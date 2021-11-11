package com.deloitte.nextgen.framework.persistence.processor;

import com.deloitte.nextgen.framework.persistence.annotations.EntityType;
import com.deloitte.nextgen.framework.persistence.enums.TypeEnum;
import com.google.common.base.CaseFormat;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author nishmehta on 20/10/2020 8:03 PM
 * @project ng-framework
 */

@Data
public class EntityTypeItem {

    private static final String TYPE_ONE_PREFIX = "";
    private static final String TYPE_ONE_SUFFIX = "A";
    private static final String SEPARATOR = "_";
    @Setter(AccessLevel.NONE)
    private final TypeElement annotatedClassElement;
    @Setter(AccessLevel.NONE)
    private final TypeEnum entityType;
    @Setter(AccessLevel.NONE)
    private final String entityPackageName;
    @Setter(AccessLevel.NONE)
    private final String entityName;
    @Setter(AccessLevel.NONE)
    private final List<AnnotationSpec> classAnnotationSpec;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final boolean multipleIdFields;
    @Setter(AccessLevel.NONE)
    private TypeMirror idClass;
    @Setter(AccessLevel.NONE)
    private List<FieldSpec> idFieldsSpec;
    @Setter(AccessLevel.NONE)
    private List<FieldSpec> classFieldsSpec;
    //For generating @Table with _B class name
    @Setter(AccessLevel.NONE)
    private List<AnnotationSpec> classBAnnotationSpec;
    @Setter(AccessLevel.NONE)
    private TypeMirror customRepository;

    public EntityTypeItem(TypeElement annotatedElement, Elements elementUtils) {
        this.annotatedClassElement = annotatedElement;

        EntityType annotation = this.annotatedClassElement.getAnnotation(EntityType.class);
        this.entityType = annotation.type();

        try {
            annotation.customRepository();
        } catch (MirroredTypeException mte) {
            this.customRepository = mte.getTypeMirror();
        }
        System.out.println("analyzing " + annotatedClassElement);

        if (this.customRepository.getKind() == TypeKind.VOID) {
            this.customRepository = null;
        }

        this.entityName = annotatedElement.getSimpleName().toString();
        this.entityPackageName = elementUtils.getPackageOf(annotatedElement).getQualifiedName().toString();
        this.idClass = ((DeclaredType) annotatedClassElement.getSuperclass()).getTypeArguments().get(0);
        this.idFieldsSpec = discoverIdFieldSpec();
        this.multipleIdFields = idFieldsSpec.size() > 1;
        this.classFieldsSpec = discoverFieldSpec();
        this.classAnnotationSpec = discoverClassAnnotationsSpec();

        if (this.entityType == TypeEnum.TWO) {
            this.classBAnnotationSpec = discoverClassBAnnotationsSpec();
        }

    }

    public String getEntityName() {
        return entityName;
    }

    public String getRepositoryName() {
        return entityName.concat("Repository");

    }

    public String getEntityPackageName() {
        return entityPackageName + ".generated";
    }

    public String getRepositoryPackageName() {
        String repositoryPackageName = entityPackageName.substring(0, entityPackageName.lastIndexOf("."));
        return repositoryPackageName + ".repository.generated";
    }

    public boolean hasMultipleIdFields() {
        return this.multipleIdFields;
    }

    private List<FieldSpec> discoverIdFieldSpec() {
        return annotatedClassElement.getEnclosedElements()                              // Get Enclosing elements
                .stream()                                                               // Open stream
                .filter(o -> o.getAnnotationsByType(Id.class).length != 0)              // Filter only fields with Id.class annotation
                .map(field ->                                                           // Map it to Field Specs
                        FieldSpec.builder(TypeName.get(field.asType()), field.getSimpleName().toString()) // Open FieldSpec builder
                                .addModifiers(field.getModifiers().toArray(new Modifier[0]))    // Add all the modifiers of original field
                                .build())                                                       // build FieldSpecs
                .collect(Collectors.toList());                                          // Collect FieldSpecs as a List
    }

    private List<FieldSpec> discoverIdFieldSpecForTableB() {

        final List<FieldSpec> idFieldSpecs = new ArrayList<>();
        annotatedClassElement.getEnclosedElements()
                .stream()
                .filter(o -> o.getKind().isField())
                .filter(o -> o.getAnnotationsByType(Id.class).length != 0)
                .forEach(field -> {

                    TypeMirror fieldType = field.asType();

                    FieldSpec.Builder f = FieldSpec.builder(TypeName.get(fieldType), field.getSimpleName().toString());
                    f.addModifiers(field.getModifiers().toArray(new Modifier[0]));
                    f.addAnnotation(Id.class);
                    idFieldSpecs.add(f.build());
                });

        //Sunil Add hardcoded EFF_BEGIN_DT with @Id
        //idFieldSpecs.add(getEffBeginDtFieldSpec(true));

        return idFieldSpecs;
    }

    private List<FieldSpec> discoverFieldSpec() {

        Map<Boolean, List<Element>> validFields = annotatedClassElement.getEnclosedElements()   // Get Enclosing elements
                .stream()                                                                       // Open stream Enclosing elements
                .filter(o -> o.getKind().isField())                                             // Filter only fields
                .filter(field -> field.getAnnotationsByType(Transient.class).length == 0)       // Filter fields without @Transient
                .filter(field -> field.getAnnotationsByType(OneToMany.class).length == 0)       // Filter fields without @Transient
                .filter(field -> field.getAnnotationsByType(ManyToOne.class).length == 0)       // Filter fields without @Transient
                .collect(Collectors.partitioningBy(field -> field.getAnnotationsByType(JoinColumn.class).length == 0));  //Partition by whether field has JoinColumn

        List<Element> fieldsWithoutJoinColumn = validFields.get(true);                         // Get fields without Join Column
        List<Element> fieldsWithJoinColumn = validFields.get(false);                           // Get fields with Join Column

        List<FieldSpec> fieldSpecs = new ArrayList<>();

        //Process all the fields without @JoinColumn and create field Specs
        fieldSpecs.addAll(fieldsWithoutJoinColumn
                .stream()                                                                   // Open stream of fields
                .map(field ->                                                               // Map to FieldSpec
                        FieldSpec.builder(TypeName.get(field.asType()), field.getSimpleName().toString())   // Open FieldSpec builder
                                .addModifiers(field.getModifiers().toArray(new Modifier[0]))    // Add and retain all modifiers
                                .addAnnotations(field.getAnnotationMirrors()                    // Add annotation
                                        .stream()                                               // Open stream on Annotation mirror
                                        .filter(m -> !m.getAnnotationType().toString().equals(GeneratedValue.class.getName()))
                                        .filter(m -> !m.getAnnotationType().toString().equals(SequenceGenerator.class.getName()))
                                        .map(AnnotationSpec::get)
                                        .collect(Collectors.toList()))
                                .build())                                                       // Build FieldSpecs
                .collect(Collectors.toList()));

        //Process all the fields with @JoinColumn and create field Specs
        fieldSpecs.addAll(fieldsWithJoinColumn
                .stream()                                                                   // Open stream of fields
                .map(field -> {                                                             // Map to FieldSpec

                    for (AnnotationMirror mirror : field.getAnnotationMirrors()) {

                        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : mirror.getElementValues().entrySet()) {

                            String key = entry.getKey().getSimpleName().toString();
                            if (key.equals("name")) {
                                String value = (String) entry.getValue().getValue();
                                value = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, value);
                                FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(Long.class, value);
                                fieldSpecBuilder.addModifiers(Modifier.PUBLIC);
                                return fieldSpecBuilder.build();
                            }
                        }
                    }
                    return null;
                })
                .collect(Collectors.toList()));

        return fieldSpecs;

    }

    private List<FieldSpec> discoverFieldSpecForTableB() {

        final List<FieldSpec> fieldSpecs = new ArrayList<>();
        annotatedClassElement.getEnclosedElements()
                .stream()
                .filter(o -> o.getKind().isField())
                .forEach(field -> {

                    TypeMirror fieldType = field.asType();
                    Iterator<? extends AnnotationMirror> annotationMirror = field.getAnnotationMirrors().iterator();
                    //System.out.println("adding field : " + field + " ,data-type : " + fieldType.getKind());

                    FieldSpec.Builder f = FieldSpec.builder(TypeName.get(fieldType), field.getSimpleName().toString());
                    f.addModifiers(field.getModifiers().toArray(new Modifier[0]));

                    while (annotationMirror.hasNext()) {
                        AnnotationMirror mirror = annotationMirror.next();

                        if (!isAutoGeneratedId(mirror.getAnnotationType().toString())) {
                            f.addAnnotation(AnnotationSpec.get(mirror));
                            f.addAnnotation(Id.class);
                        }
                    }

                    fieldSpecs.add(f.build());

                });

        //Sunil Add hardcoded EFF_BEGIN_DT without @Id
        //fieldSpecs.add(getEffBeginDtFieldSpec(false));

        return fieldSpecs;
    }

    private boolean isAutoGeneratedId(String annotationName) {
        return annotationName.equals(GeneratedValue.class.getName()) || annotationName.equals(GenericGenerator.class.getName());
    }

    private List<AnnotationSpec> discoverClassAnnotationsSpec() {

        final List<AnnotationSpec> annotationSpecs = new ArrayList<>();
        annotatedClassElement.getAnnotationMirrors()
                .stream()
                .filter(annotationMirror -> !annotationMirror.getAnnotationType().toString().equals(EntityType.class.getName()))
                .filter(annotationMirror -> !annotationMirror.getAnnotationType().toString().equals(SequenceGenerators.class.getName()))
                .filter(annotationMirror -> !annotationMirror.getAnnotationType().toString().equals(SequenceGenerator.class.getName()))
                .filter(annotationMirror -> !annotationMirror.getAnnotationType().toString().equals(IdClass.class.getName()))
                .forEach(annotationMirror -> {
                    if (annotationMirror.getAnnotationType().toString().equals(Table.class.getName())) {
                        Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();
                        AnnotationSpec.Builder builder = AnnotationSpec.builder(Table.class);

                        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {

                            String key = entry.getKey().getSimpleName().toString();
                            if (key.equals("uniqueConstraints") || key.equals("indexes")) {
                                continue;
                            }

                            String value = (String) entry.getValue().getValue();
                            if (key.equals("name")) {
                                value = getTableName(value);
                            }

                            builder.addMember(entry.getKey().getSimpleName().toString(), String.format("\"%s\"", value));
                        }

                        annotationSpecs.add(builder.build());
                    } else {
                        annotationSpecs.add(AnnotationSpec.get(annotationMirror).toBuilder().build());
                    }
                });

        return annotationSpecs;
    }

    private List<AnnotationSpec> discoverClassBAnnotationsSpec() {

        //this.entityType == TypeEnum.TWO

        final List<AnnotationSpec> annotationSpecs = new ArrayList<>();
        annotatedClassElement.getAnnotationMirrors()
                .stream()
                .filter(annotationMirror -> !annotationMirror.getAnnotationType().equals(EntityType.class))
                .forEach(annotationMirror -> {
                    if (annotationMirror.getAnnotationType().toString().equals(Table.class.getName())) {
                        Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues = annotationMirror.getElementValues();
                        AnnotationSpec.Builder builder = AnnotationSpec.builder(Table.class);

                        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                            String value = (String) entry.getValue().getValue();
                            String key = entry.getKey().getSimpleName().toString();
                            //if (key.equals("name")) {
                            //    value = getTableName(value);
                            //}
                            value = value + "_B";
                            String finalValue = String.format("\"%s\"", value);
                            builder.addMember(entry.getKey().getSimpleName().toString(), finalValue);
                        }

                        annotationSpecs.add(builder.build());
                    } else {
                        if (!(annotationMirror.getAnnotationType().toString().equals(EntityType.class.getName())
                                || annotationMirror.getAnnotationType().toString().equals(IdClass.class.getName()))) {
                            annotationSpecs.add(AnnotationSpec.get(annotationMirror).toBuilder().build());
                        }
                    }
                });

        return annotationSpecs;
    }


    public String getTableName(String tableName) {
        return TYPE_ONE_PREFIX +
                tableName +
                SEPARATOR +
                TYPE_ONE_SUFFIX;
    }


}
