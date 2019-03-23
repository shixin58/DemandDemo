package com.max.processor;

import com.google.auto.service.AutoService;
import com.max.annotations.Template;

import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

/**
 * <p>Created by shixin on 2019/3/23.
 */
@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        StringBuilder sb = new StringBuilder("package com.max.processor;\n")
                .append("public class TemplateGenerated {\n")
                .append("\tpublic String getMessage() {\n")
                .append("\t\treturn \"");
        for(Element element : roundEnvironment.getElementsAnnotatedWith(Template.class)) {
            sb.append(element.getSimpleName()).append(" say hello!\\n");
        }
        sb.append("\";\n")
                .append("\t}\n")
                .append("}\n");
        try {
            JavaFileObject source = processingEnv.getFiler()
                    .createSourceFile("com.max.processor.TemplateGenerated");
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
        annotations.add(Template.class);
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }
}
