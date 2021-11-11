package com.deloitte.nextgen.framework.persistence.processor;

import com.squareup.javapoet.JavaFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public class EntityZeroGenerator extends AbstractEntityGenerator {

    public EntityZeroGenerator(EntityTypeItem item) {
        super(item);
    }

    @Override
    public List<JavaFile> generate() {
        List<JavaFile> javaFile = new ArrayList<>();
        javaFile.addAll(generatePrimaryKey());
        return javaFile;
    }
}
