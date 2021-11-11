package com.deloitte.nextgen.framework.automate;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.spi.AnnotationProcessingEnvironment;
import com.deloitte.nextgen.framework.automate.spi.EntityTypeFileGenerator;
import com.deloitte.nextgen.framework.automate.spi.impl.DefaultEntityTypeFileGeneratorFactory;
import com.squareup.javapoet.JavaFile;

import java.io.IOException;

/**
 * @author nishmehta
 * @since 05/07/2021 4:27 PM
 */
public class GeneratorFactory {

    private final AnnotationProcessingEnvironment processorContext;
    private final EndpointDefaultValue defaultValue;

    public GeneratorFactory(AnnotationProcessingEnvironment processorContext, EndpointDefaultValue defaultValue) {
        this.processorContext = processorContext;
        this.defaultValue = defaultValue;
    }

    public void generate() {

        EntityTypeFileGenerator entityTypeFileGenerator = null;

        switch (defaultValue.type()) {
            case NONE:
            case ZERO:
            case ONE:
                entityTypeFileGenerator = new DefaultEntityTypeFileGeneratorFactory(processorContext.packageName(), defaultValue, processorContext.importOperation());
                break;
            case TWO:
                throw new UnsupportedOperationException("Could not generate file for type 2 entity");
        }

        FilesGenerator app = new FilesGenerator(entityTypeFileGenerator);
        app.collect().stream().forEach(fileData -> {
            JavaFile jf = JavaFile.builder(fileData.getPackageName(), fileData.getTypeSpec()).build();

            try {
                jf.writeTo(processorContext.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

