package com.deloitte.nextgen.framework.persistence.processor;

import com.squareup.javapoet.JavaFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunshah on 03/16/2021 8:03 PM
 * @project ng-framework
 */
public class EntityTwoGenerator extends AbstractEntityGenerator {

    public EntityTwoGenerator(EntityTypeItem item){
        super(item);
    }

    @Override
    public List<JavaFile> generate() {
        List<JavaFile> javaFile = new ArrayList();
        javaFile.addAll(generatePrimaryKey());
        javaFile.add(generateAuditEntity());
        javaFile.add(generateBEntity());
        //javaFile.add(generateRepository());
        return javaFile;
    }
}
