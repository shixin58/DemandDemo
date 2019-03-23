package com.max.processor;

import com.google.auto.service.AutoService;
import com.max.annotations.PageInfo;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * <p>Created by shixin on 2019/3/23.
 */
@AutoService(Processor.class)
public class PageRouterProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnv.getMessager();

        StringBuilder sb = new StringBuilder("package com.max.processor;\n\n")
                .append("import java.util.HashMap;\n")
                .append("import java.util.Map;\n\n")
                .append("public class PageInfoGenerated {\n")
                .append("public static final Map<String, String> MAP = new HashMap<>();\n")
                .append("\tpublic static void savePages() {\n");
        for(Element element : roundEnvironment.getElementsAnnotatedWith(PageInfo.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        String.format("Only classes can be annotated with @%s", PageInfo.class.getName()), element);
                return true;
            }
            TypeElement typeElement = (TypeElement) element;
            PageInfo pageInfo = typeElement.getAnnotation(PageInfo.class);
            sb.append("\t\t").append("MAP.put(\"").append(pageInfo.alias()).append("\", \"")
                    .append(typeElement.asType()).append("\");\n");
        }
        sb.append("\t}\n")
                .append("}\n");
        try {
            JavaFileObject source = processingEnv.getFiler()
                    .createSourceFile("com.max.processor.PageInfoGenerated");
            Writer writer = source.openWriter();
            writer.write(sb.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        annotations.add(PageInfo.class);
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
}
