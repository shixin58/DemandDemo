package com.max.processor;

import com.google.auto.service.AutoService;
import com.max.annotations.Param;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * <p>Created by shixin on 2019/4/12.
 */
@AutoService(Processor.class)
public class ParamRouterProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnv.getMessager();
        HashMap<TypeElement, HashSet<VariableElement>> map = new HashMap<>();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Param.class)) {
            if (element.getKind() != ElementKind.FIELD) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        String.format("Only fields can be annotated with @%s", Param.class.getName()), element);
                return true;
            }
            TypeElement typeElement = (TypeElement) element.getEnclosingElement();
            if (map.get(typeElement) == null) {
                map.put(typeElement, new HashSet<>());
            }
            map.get(typeElement).add((VariableElement) element);
        }
        for (Map.Entry<TypeElement, HashSet<VariableElement>> entry : map.entrySet()) {
            TypeElement typeElement = entry.getKey();
            HashSet<VariableElement> elements = entry.getValue();
            String packageName = typeElement.asType().toString().substring(0, typeElement.asType().toString().lastIndexOf("."));
            String simpleClsName = typeElement.getSimpleName()+"_InjectParams";
            StringBuilder sb = new StringBuilder("package ").append(packageName).append(";\n\n")
                    .append("import com.max.compiler.RouterUtils;\n\n")
                    .append("public class ").append(simpleClsName).append(" {\n\n")
                    .append("\tpublic static void inject(").append(typeElement.getSimpleName()).append(" activity) {\n");
            for (VariableElement variableElement:elements) {
                Param param = variableElement.getAnnotation(Param.class);
                String name = variableElement.asType().toString();
                String simpleName = name.lastIndexOf(".") >= 0 ? name.substring(name.lastIndexOf(".")+1):name;
                sb.append("\t\tactivity.").append(variableElement.getSimpleName()).append(" = RouterUtils.find(")
                        .append(simpleName).append(".class, ")
                        .append("activity.getIntent(), ")
                        .append('\"').append(param.name()).append("\");\n");
            }
            sb.append("\t}").append("\n}");

            try {
                JavaFileObject source = processingEnv.getFiler()
                        .createSourceFile(packageName+"."+simpleClsName);
                Writer writer = source.openWriter();
                writer.write(sb.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : getSupportedAnnotations()) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(Param.class);
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
}
