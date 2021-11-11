package com.deloitte.nextgen.framework.automate.spi;

import com.deloitte.nextgen.framework.automate.utils.FileType;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import java.util.List;

/**
 * @author nishmehta
 * @since 05/07/2021 4:32 PM
 */
public interface Metadata {

    String NOT_SUPPORTED = "Not Supported";

    FileType type();

    String packageName();

    String className();

    List<AnnotationSpec> annotations();

    List<FieldSpec> members();

    List<MethodSpec> methods();

    List<TypeName> superInterfaces();

}

