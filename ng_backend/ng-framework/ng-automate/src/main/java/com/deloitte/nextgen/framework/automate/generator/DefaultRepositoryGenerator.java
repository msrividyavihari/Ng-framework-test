package com.deloitte.nextgen.framework.automate.generator;

import com.deloitte.nextgen.framework.automate.exceptions.InitializationException;
import com.deloitte.nextgen.framework.automate.spi.Generator;
import com.deloitte.nextgen.framework.automate.spi.Metadata;
import com.squareup.javapoet.TypeSpec;
import lombok.Getter;

import javax.lang.model.element.Modifier;

/**
 * @author nishmehta
 * @since 05/07/2021 3:52 PM
 */
public class DefaultRepositoryGenerator implements Generator {

    @Getter
    private final Metadata metadata;

    private TypeSpec typeSpec;

    public DefaultRepositoryGenerator(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public TypeSpec type() {
        if (typeSpec == null) {
            throw new InitializationException("Type not initialized. Please call generate() method before calling type() method");
        }

        return typeSpec;
    }

    @Override
    public void generate() {

        if (typeSpec != null)
            return;

        typeSpec = TypeSpec.interfaceBuilder(metadata.className())
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterfaces(metadata.superInterfaces())
                .build();
    }
}
