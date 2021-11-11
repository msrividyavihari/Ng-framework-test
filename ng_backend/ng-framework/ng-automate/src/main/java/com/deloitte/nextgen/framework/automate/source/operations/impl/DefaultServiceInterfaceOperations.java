package com.deloitte.nextgen.framework.automate.source.operations.impl;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.squareup.javapoet.MethodSpec;

import javax.lang.model.element.Modifier;

/**
 * @author nishmehta
 * @since 07/07/2021 3:05 PM
 */
public class DefaultServiceInterfaceOperations extends AbstractServiceOperations {

    public DefaultServiceInterfaceOperations(EndpointDefaultValue endpointDefaultValue) {
        super(endpointDefaultValue);
    }

    @Override
    public MethodSpec create() {
        return createMethod().addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).build();
    }

    @Override
    public MethodSpec update() {
        return updateMethod().addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).build();
    }

    @Override
    public MethodSpec delete() {
        return deleteMethod().addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).build();
    }

    @Override
    public MethodSpec getOne() {
        return getOneMethod().addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).build();
    }

    @Override
    public MethodSpec getAll() {
        return getAllMethod().addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).build();
    }
}
