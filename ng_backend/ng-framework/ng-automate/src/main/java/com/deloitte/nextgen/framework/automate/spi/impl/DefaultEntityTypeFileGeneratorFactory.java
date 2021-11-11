package com.deloitte.nextgen.framework.automate.spi.impl;

import com.deloitte.nextgen.framework.automate.generator.DefaultRepositoryGenerator;
import com.deloitte.nextgen.framework.automate.generator.DefaultResourceGenerator;
import com.deloitte.nextgen.framework.automate.generator.DefaultServiceImplGenerator;
import com.deloitte.nextgen.framework.automate.generator.DefaultServiceInterfaceGenerator;
import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.DefaultRepositorySource;
import com.deloitte.nextgen.framework.automate.source.DefaultResourceSource;
import com.deloitte.nextgen.framework.automate.source.DefaultServiceImplSource;
import com.deloitte.nextgen.framework.automate.source.DefaultServiceInterfaceSource;
import com.deloitte.nextgen.framework.automate.spi.EntityTypeFileGenerator;
import com.deloitte.nextgen.framework.automate.spi.Generator;
import com.deloitte.nextgen.framework.automate.spi.Metadata;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.type.TypeMirror;

/**
 * @author nishmehta
 * @since 06/07/2021 11:40 AM
 */
public class DefaultEntityTypeFileGeneratorFactory implements EntityTypeFileGenerator {

    private final EndpointDefaultValue endpointDefaultValue;
    private final String basePackage;
    private final TypeMirror operations;


    public DefaultEntityTypeFileGeneratorFactory(String basePackage, EndpointDefaultValue endpointDefaultValue, TypeMirror operations) {
        this.basePackage = basePackage;
        this.endpointDefaultValue = endpointDefaultValue;
        this.operations = operations;
    }

    @Override
    public Generator createRepositoryInterface() {
        Metadata metadata = new DefaultRepositorySource(basePackage, endpointDefaultValue);
        return new DefaultRepositoryGenerator(metadata);
    }

    @Override
    public Generator createServiceInterface() {
        Metadata metadata = new DefaultServiceInterfaceSource(basePackage, endpointDefaultValue);
        return new DefaultServiceInterfaceGenerator(metadata);
    }

    @Override
    public Generator createServiceImpl(TypeSpec serviceInterface, TypeSpec repository) {
        Metadata metadata = new DefaultServiceImplSource(basePackage, endpointDefaultValue, serviceInterface, repository);
        return new DefaultServiceImplGenerator(metadata);
    }

    @Override
    public Generator createResourceImpl(TypeSpec serviceInterface) {
        Metadata metadata = new DefaultResourceSource(basePackage, endpointDefaultValue, serviceInterface, operations);
        return new DefaultResourceGenerator(metadata);
    }
}
