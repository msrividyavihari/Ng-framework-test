package com.deloitte.nextgen.framework.automate.source;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.deloitte.nextgen.framework.automate.source.operations.impl.DefaultServiceInterfaceOperations;
import com.deloitte.nextgen.framework.automate.utils.FileType;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;

import java.util.List;

/**
 * @author nishmehta
 * @since 05/07/2021 7:15 PM
 */
public class DefaultServiceInterfaceSource extends AbstractSourceFile {

    private Operations operations;

    public DefaultServiceInterfaceSource(String basePackage, EndpointDefaultValue endpointDefaultValue) {
        super(basePackage, endpointDefaultValue);
        this.operations = new DefaultServiceInterfaceOperations(endpointDefaultValue);
    }

    @Override
    public Operations operations() {
        return new DefaultServiceInterfaceOperations(endpointDefaultValue);
    }

    @Override
    public FileType type() {
        return FileType.SERVICE_INTERFACE;
    }

    @Override
    public List<AnnotationSpec> annotations() {
        throw new UnsupportedOperationException("Not Supported");
    }

    @Override
    public List<FieldSpec> members() {
        throw new UnsupportedOperationException("Not Supported");
    }

}
