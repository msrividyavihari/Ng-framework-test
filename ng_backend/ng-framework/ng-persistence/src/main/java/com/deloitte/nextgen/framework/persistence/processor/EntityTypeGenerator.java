package com.deloitte.nextgen.framework.persistence.processor;

import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.squareup.javapoet.JavaFile;

import javax.annotation.processing.Filer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */
public class EntityTypeGenerator {

    private final Filer filer;

    private Map<TypeEnum, List<EntityGenerator>> entityTypeMap;

    public EntityTypeGenerator(Filer filer) {
        this.filer = filer;
        entityTypeMap = new HashMap<>();
    }

    public void put(TypeEnum type, EntityTypeItem item) {
        if (entityTypeMap.containsKey(type)) {
            entityTypeMap.get(type).add(createGenerator(type, item));
        } else {
            List<EntityGenerator> entityTypeAnnotationItems = new ArrayList<>();
            entityTypeAnnotationItems.add(createGenerator(type, item));
            entityTypeMap.put(type, entityTypeAnnotationItems);
        }
    }

    private EntityGenerator createGenerator(TypeEnum typeEnum, EntityTypeItem item) {

        if (typeEnum.equals(TypeEnum.ZERO)) {
            return new EntityZeroGenerator(item);
        }

        if (typeEnum.equals(TypeEnum.ONE)) {
            return new EntityOneGenerator(item);
        }

        //Type 2 Logic
        if (typeEnum.equals(TypeEnum.TWO)) {
            return new EntityTwoGenerator(item);
        }

        throw new IllegalArgumentException("No such type exist " + typeEnum);
    }

    public void process() {

        List<JavaFile> files = new ArrayList<>();
        entityTypeMap.entrySet()
                .stream()
                .forEach(typeEnumListEntry -> typeEnumListEntry.getValue()
                        .stream()
                        .forEach(entityGenerator -> files.addAll(entityGenerator.generate())));

        files.stream().forEach(javaFile -> {
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
