package com.deloitte.nextgen.framework.automate.spi;

import com.squareup.javapoet.TypeSpec;

/**
 * @author nishmehta
 * @since 06/07/2021 11:21 AM
 */
public interface EntityTypeFileGenerator {

    Generator createRepositoryInterface();

    Generator createServiceInterface();

    Generator createServiceImpl(TypeSpec serviceInterface, TypeSpec repository);

    Generator createResourceImpl(TypeSpec serviceInterface);

}
