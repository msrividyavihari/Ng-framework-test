package com.deloitte.nextgen.framework.persistence.processor;

import com.deloitte.nextgen.framework.commons.enums.HistoryNavInd;
import com.deloitte.nextgen.framework.persistence.entity.type.one.TypeOneAuditBaseEntity;
import com.deloitte.nextgen.framework.persistence.entity.type.two.TypeTwoEntity;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.squareup.javapoet.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Modifier;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author nishmehta on 23/11/2020 1:00 PM
 * @project ng-framework
 */
public abstract class AbstractEntityGenerator implements EntityGenerator {

    private static final String AUDIT_CLASS_SUFFIX = "A";

    private static final String AUDIT_CLASS_PK_SUFFIX = "Audit";

    private final EntityTypeItem annotationItem;

    private AnnotationSpec auditIdClass;

    private final String B_CLASS_SUFFIX = "B";

    private final String BASE_CLASS_PK_SUFFIX = "Base";

    private AnnotationSpec annotationSpecIdClass;

    protected AbstractEntityGenerator(EntityTypeItem item) {
        this.annotationItem = item;
    }

    public EntityTypeItem getAnnotationItem() {
        return annotationItem;
    }

    protected List<JavaFile> generatePrimaryKey() {

        List<JavaFile> files = new ArrayList<>();

        if (annotationItem.hasMultipleIdFields()) {
            JavaFile basePK =  generatePrimaryKey(annotationItem.getEntityName() + "PK");
            files.add(basePK);
        }

        if (annotationItem.getEntityType().equals(TypeEnum.ONE)
                || annotationItem.getEntityType().equals(TypeEnum.TWO)) {
            JavaFile auditPK = generateAuditPrimaryKey();
            files.add(auditPK);
        }

        //Type 2 Logic for PK file
        if (annotationItem.getEntityType().equals(TypeEnum.TWO)) {
            JavaFile bPK = generateBasePrimaryKey();

            if (bPK != null) {
                files.add(bPK);
            }
        }

        return files;
    }


    private JavaFile generateBasePrimaryKey() {
        String bIdClass = getAnnotationItem().getEntityName() + BASE_CLASS_PK_SUFFIX + "PK";

        this.annotationSpecIdClass = AnnotationSpec.builder(IdClass.class).addMember("value", bIdClass + ".class").build();

        return generateBasePrimaryKey(bIdClass);
    }

    private JavaFile generateAuditPrimaryKey() {
        String auditIdClassName = getAnnotationItem().getEntityName() + AUDIT_CLASS_PK_SUFFIX + "PK";

        this.auditIdClass = AnnotationSpec.builder(IdClass.class).addMember("value", auditIdClassName + ".class").build();

        return generatePrimaryKey(auditIdClassName, getHistorySeqFieldSpec(false));
    }

    private JavaFile generatePrimaryKey(String className, FieldSpec... extraFields) {

        TypeSpec.Builder typeSpec = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Serializable.class)
                .addAnnotation(Data.class)
                .addAnnotation(AllArgsConstructor.class)
                .addAnnotation(NoArgsConstructor.class)
                .addFields(annotationItem.getIdFieldsSpec());

        List<FieldSpec> fs = Stream.of(extraFields).filter(Objects::nonNull).collect(Collectors.toList());
        if (!fs.isEmpty()) {
            typeSpec.addFields(fs);
        }
        return JavaFile.builder(annotationItem.getEntityPackageName(), typeSpec.build()).build();

    }
    private JavaFile generateBasePrimaryKey(String className, FieldSpec... extraFields) {

        TypeSpec.Builder typeSpec = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Serializable.class)
                .addAnnotation(Data.class)
                .addAnnotation(AllArgsConstructor.class)
                .addAnnotation(NoArgsConstructor.class)
                .addFields(getAnnotationItem()
                        .getIdFieldsSpec()
                        .stream()
                        .filter( field -> !field.name.equalsIgnoreCase("effBeginDt"))
                        .collect(Collectors.toList())
                );

        List<FieldSpec> fs = Stream.of(extraFields).filter(Objects::nonNull).collect(Collectors.toList());
        if (!fs.isEmpty()) {
            typeSpec.addFields(fs);
        }


        return JavaFile.builder(annotationItem.getEntityPackageName(), typeSpec.build()).build();

    }

    protected JavaFile generateAuditEntity() {

        TypeSpec typeSpec = null;

        //Add histNavInd for Type 2 classes
        if (annotationItem.getEntityType().equals(TypeEnum.TWO)) {

            typeSpec = TypeSpec.classBuilder(getAnnotationItem().getEntityName() + AUDIT_CLASS_SUFFIX)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(ParameterizedTypeName.get(ClassName.get(TypeOneAuditBaseEntity.class), ClassName.bestGuess(getAnnotationItem().getEntityName() + AUDIT_CLASS_PK_SUFFIX + "PK")))
                    //.superclass(ClassName.get(TypeOneAuditBaseEntity.class))
                    .addAnnotations(getAnnotationItem().getClassAnnotationSpec())
                    .addAnnotation(auditIdClass)
                    //.addAnnotation(tableNameSpec)
                    .addField(getHistorySeqFieldSpec(true))
                    .addField(getHistNavIndFieldSpec(false))
//                    .addField(getEffBeginDtFieldSpec(false))
                    .addField(getEffEndDtFieldSpec(false))
                    .addFields(getAnnotationItem().getClassFieldsSpec())
                    .build();

        } else {

            typeSpec = TypeSpec.classBuilder(getAnnotationItem().getEntityName() + AUDIT_CLASS_SUFFIX)
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(ParameterizedTypeName.get(ClassName.get(TypeOneAuditBaseEntity.class), ClassName.bestGuess(getAnnotationItem().getEntityName() + AUDIT_CLASS_PK_SUFFIX + "PK")))
                    //.superclass(ClassName.get(TypeOneAuditBaseEntity.class))
                    .addAnnotations(getAnnotationItem().getClassAnnotationSpec())
                    .addAnnotation(auditIdClass)
                    .addField(getHistorySeqFieldSpec(true))
                    .addFields(getAnnotationItem().getClassFieldsSpec())
                    .build();

        }
        return JavaFile.builder(getAnnotationItem().getEntityPackageName(), typeSpec).build();

    }

    private FieldSpec getHistorySeqFieldSpec(boolean isField) {
        FieldSpec.Builder f = FieldSpec.builder(TypeName.get(Long.class), "historySeq");
        f.addModifiers(Modifier.PRIVATE);
        if (isField) {
            f.addAnnotation(Id.class);
        }
        return f.build();
    }

    protected JavaFile generateBEntity() {

        TypeSpec typeSpec = TypeSpec.classBuilder(getAnnotationItem().getEntityName() + B_CLASS_SUFFIX)
                .addModifiers(Modifier.PUBLIC)
                .superclass(ParameterizedTypeName.get(ClassName.get(TypeTwoEntity.class), ClassName.bestGuess(getAnnotationItem().getEntityName() + BASE_CLASS_PK_SUFFIX + "PK")))
//                .superclass(ClassName.get(TypeTwoEntity.class))
                //New Class Annotation Spec for @Table with _B name
                .addAnnotations(getAnnotationItem().getClassBAnnotationSpec())
                .addAnnotation(annotationSpecIdClass)
//                .addField(getEffBeginDtFieldSpec(false))
//                .addField(getHistorySeqFieldSpec(false))
                //.addFields(getAnnotationItem().getClassFieldsSpec())

                .addFields(getAnnotationItem()
                                .getClassFieldsSpec()
                                .stream()
                                .filter( e -> e.annotations.contains(AnnotationSpec.builder(Id.class).build()))
                                .filter( field -> !field.name.equalsIgnoreCase("effBeginDt"))
                                .collect(Collectors.toList())
                )
                .build();
        return JavaFile.builder(getAnnotationItem().getEntityPackageName(), typeSpec).build();

    }

    protected JavaFile generateRepository() {

        TypeSpec.Builder typeSpec = TypeSpec.interfaceBuilder(getAnnotationItem().getRepositoryName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Repository.class)
                .addSuperinterface(ParameterizedTypeName.get(
                        ClassName.get(JpaRepository.class),
                        TypeName.get(getAnnotationItem().getAnnotatedClassElement().asType()),
                        TypeName.get(getAnnotationItem().getIdClass())
                ));
        if (getAnnotationItem().getCustomRepository() != null) {
            typeSpec.addSuperinterface(getAnnotationItem().getCustomRepository());
        }

        return JavaFile.builder(getAnnotationItem().getRepositoryPackageName(), typeSpec.build()).build();
    }

    private FieldSpec getEffBeginDtFieldSpec(boolean isField) {
        //With or without @Id annotation based on boolean isField true/false
        FieldSpec.Builder f = FieldSpec.builder(TypeName.get(java.util.Date.class), "effBeginDt");
        f.addModifiers(Modifier.PRIVATE);
        if (isField) {
            f.addAnnotation(Id.class);
        }
        return f.build();
    }

    private FieldSpec getEffEndDtFieldSpec(boolean isField) {
        //With or without @Id annotation based on boolean isField true/false
        FieldSpec.Builder f = FieldSpec.builder(TypeName.get(java.time.LocalDate.class), "effEndDt");
        f.addModifiers(Modifier.PRIVATE);
        if (isField) {
            f.addAnnotation(Id.class);
        }
        return f.build();
    }

    private FieldSpec getHistNavIndFieldSpec(boolean isField) {
        FieldSpec.Builder f = FieldSpec.builder(TypeName.get(HistoryNavInd.class), "histNavInd");
        f.addModifiers(Modifier.PRIVATE);
        if (isField) {
            f.addAnnotation(Id.class);
        }
       // @Enumerated(EnumType.STRING)
        AnnotationSpec annotationSpec =  AnnotationSpec.builder(Enumerated.class).addMember("value","javax.persistence.EnumType.STRING").build();

        f.addAnnotation(annotationSpec);
        return f.build();
    }

}

