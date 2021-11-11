package com.deloitte.nextgen.framework.automate.source;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.deloitte.nextgen.framework.automate.spi.Metadata;
import com.deloitte.nextgen.framework.automate.utils.FileType;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.List;

import static com.deloitte.nextgen.framework.automate.source.NameConstants.*;

/**
 * @author nishmehta
 * @since 05/07/2021 6:20 PM
 */
public abstract class AbstractSourceFile implements Metadata {

    protected final String basePackage;

    protected final EndpointDefaultValue endpointDefaultValue;

    protected AbstractSourceFile(String basePackage, EndpointDefaultValue endpointDefaultValue) {
        this.basePackage = basePackage;
        this.endpointDefaultValue = endpointDefaultValue;
    }

    public abstract Operations operations();

    @Override
    public List<TypeName> superInterfaces() {

        throw new UnsupportedOperationException("Not Supported");
    }

    protected String getPackageName(FileType type) {

        String packageName = basePackage.concat(GENERATED_PACKAGE);

        switch (type) {
            case RESOURCE_IMPL:
                return packageName.concat(RESOURCE_PACKAGE);
            case SERVICE_INTERFACE:

                return packageName.concat(SERVICE_PACKAGE);
            case SERVICE_IMPL:

                return packageName.concat(SERVICE_IMPL_PACKAGE);
            case REPOSITORY_INTERFACE:
                return packageName.concat(REPOSITORY_PACKAGE);
            default:
                throw new UnsupportedOperationException(type.name());
        }
    }

    protected String getClassName(FileType type) {

        String simpleTypeName = endpointDefaultValue.className();
        switch (type) {
            case RESOURCE_IMPL:
                return simpleTypeName.concat(RESOURCE_SUFFIX);

            case SERVICE_INTERFACE:
                return simpleTypeName.concat(SERVICE_SUFFIX);

            case SERVICE_IMPL:
                return simpleTypeName.concat(SERVICE_SUFFIX).concat(IMPL_SUFFIX);

            case REPOSITORY_INTERFACE:
                return simpleTypeName.concat(REPOSITORY_SUFFIX);

            default:
                throw new UnsupportedOperationException(type.name());
        }
    }

    @Override
    public String packageName() {
        return getPackageName(type());
    }

    @Override
    public String className() {
        return getClassName(type());
    }

    @Override
    public List<MethodSpec> methods() {

        List<MethodSpec> methodSpecs = new ArrayList<>();
        if (operations() == null) {
            return methodSpecs;
        }


        methodSpecs.add(operations().create());
        methodSpecs.add(operations().update());
        methodSpecs.add(operations().delete());
        methodSpecs.add(operations().getOne());
        methodSpecs.add(operations().getAll());

        return methodSpecs;
    }
}
