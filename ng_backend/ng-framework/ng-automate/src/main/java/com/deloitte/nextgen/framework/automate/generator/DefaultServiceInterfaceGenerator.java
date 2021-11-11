package com.deloitte.nextgen.framework.automate.generator;

import com.deloitte.nextgen.framework.automate.spi.Generator;
import com.deloitte.nextgen.framework.automate.spi.Metadata;
import com.squareup.javapoet.TypeSpec;
import lombok.Getter;

import javax.lang.model.element.Modifier;

/**
 * @author nishmehta
 * @since 05/07/2021 3:53 PM
 */
public class DefaultServiceInterfaceGenerator implements Generator {

    @Getter
    private final Metadata metadata;

    private TypeSpec serviceInterface;

    public DefaultServiceInterfaceGenerator(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public TypeSpec type() {
        return serviceInterface;
    }

    @Override
    public void generate() {
        if (serviceInterface != null)
            return;

        serviceInterface = TypeSpec.interfaceBuilder(metadata.className())
                .addModifiers(Modifier.PUBLIC)
                .addMethods(metadata.methods())
                .build();

    }
}
