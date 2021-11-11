package com.deloitte.nextgen.framework.automate.source;

import com.deloitte.nextgen.framework.automate.processor.EndpointDefaultValue;
import com.deloitte.nextgen.framework.automate.source.operations.Operations;
import com.deloitte.nextgen.framework.automate.spi.Metadata;
import com.deloitte.nextgen.framework.automate.utils.FileType;
import com.squareup.javapoet.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta
 * @since 05/07/2021 10:43 PM
 */
public class DefaultRepositorySource extends AbstractSourceFile {


    public DefaultRepositorySource(String basePackage, EndpointDefaultValue endpointDefaultValue) {
        super(basePackage, endpointDefaultValue);
    }

    @Override
    public Operations operations() {
        return null;
    }

    @Override
    public FileType type() {
        return FileType.REPOSITORY_INTERFACE;
    }

    @Override
    public List<AnnotationSpec> annotations() {
        throw new UnsupportedOperationException(Metadata.NOT_SUPPORTED);
    }

    @Override
    public List<FieldSpec> members() {
        throw new UnsupportedOperationException(Metadata.NOT_SUPPORTED);
    }

    @Override
    public List<MethodSpec> methods() {
        throw new UnsupportedOperationException(Metadata.NOT_SUPPORTED);
    }

    @Override
    public List<TypeName> superInterfaces() {

        List<TypeName> typeNames = new ArrayList<>();

        TypeName entity = TypeName.get(endpointDefaultValue.entity());

        TypeName jpaRepo = ParameterizedTypeName.get(ClassName.get(JpaRepository.class), entity, TypeName.get(endpointDefaultValue.primaryKey()));
        typeNames.add(jpaRepo);

        jpaRepo = ParameterizedTypeName.get(ClassName.get(QuerydslPredicateExecutor.class), entity);
        typeNames.add(jpaRepo);

        return typeNames;
    }
}
