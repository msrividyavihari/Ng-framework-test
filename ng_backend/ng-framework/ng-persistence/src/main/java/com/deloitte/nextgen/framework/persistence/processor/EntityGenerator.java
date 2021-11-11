package com.deloitte.nextgen.framework.persistence.processor;

import com.squareup.javapoet.JavaFile;

import java.util.List;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

public interface EntityGenerator {

    List<JavaFile> generate();
}
