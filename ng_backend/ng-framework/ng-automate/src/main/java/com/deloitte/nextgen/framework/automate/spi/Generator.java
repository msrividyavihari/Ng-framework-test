package com.deloitte.nextgen.framework.automate.spi;

import com.squareup.javapoet.TypeSpec;

/**
 * @author nishmehta
 * @since 05/07/2021 1:49 PM
 */
public interface Generator {

    Metadata getMetadata();

    TypeSpec type();

    void generate();
}
