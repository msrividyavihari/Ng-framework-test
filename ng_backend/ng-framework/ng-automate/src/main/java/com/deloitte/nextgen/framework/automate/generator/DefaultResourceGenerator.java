package com.deloitte.nextgen.framework.automate.generator;

import com.deloitte.nextgen.framework.automate.exceptions.InitializationException;
import com.deloitte.nextgen.framework.automate.spi.Generator;
import com.deloitte.nextgen.framework.automate.spi.Metadata;
import com.squareup.javapoet.TypeSpec;
import lombok.Getter;

import javax.lang.model.element.Modifier;
import java.util.Optional;

/**
 * @author nishmehta
 * @since 05/07/2021 3:53 PM
 */
public class DefaultResourceGenerator implements Generator {

    @Getter
    private final Metadata metadata;

    private TypeSpec typeSpec;

    public DefaultResourceGenerator(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public TypeSpec type() {
        return Optional.ofNullable(typeSpec)
                .orElseThrow(() -> new InitializationException("Type not initialized. Please call generate() method before calling type() method"));
    }

    @Override
    public void generate() {

        if (typeSpec != null) return;

        typeSpec = TypeSpec.classBuilder(metadata.className())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotations(metadata.annotations())
                .addFields(metadata.members())
                .addMethods(metadata.methods())
                .build();
    }
}
