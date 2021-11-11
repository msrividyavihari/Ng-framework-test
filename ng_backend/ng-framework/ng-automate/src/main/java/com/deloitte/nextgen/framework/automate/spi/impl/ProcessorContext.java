package com.deloitte.nextgen.framework.automate.spi.impl;

import com.deloitte.nextgen.framework.automate.spi.AnnotationProcessingEnvironment;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nishmehta
 * @since 22/07/2021 11:38 AM
 */
public class ProcessorContext implements AnnotationProcessingEnvironment {

    private final Set<String> processed = new HashSet<>();
    private final Filer filer;
    private Elements elementUtils;
    private Types typeUtils;
    private Messager messager;
    private boolean verbose;

    private String packageName;
    private TypeMirror importOperation;

    public ProcessorContext(Elements elementUtils, Types typeUtils, Messager messager, Filer filer, boolean verbose) {
        this.elementUtils = elementUtils;
        this.typeUtils = typeUtils;
        this.messager = messager;
        this.verbose = verbose;
        this.filer = filer;
    }

    @Override
    public Elements getElementUtils() {
        return elementUtils;
    }

    @Override
    public Types getTypeUtils() {
        return typeUtils;
    }

    @Override
    public Filer getFiler() {
        return filer;
    }

    @Override
    public Messager getMessager() {
        return messager;
    }

    @Override
    public boolean isVerbose() {
        return verbose;
    }

    @Override
    public void addProcessed(String name) {
        processed.add(name);
    }

    @Override
    public boolean isProcessed(String name) {
        return processed.contains(name);
    }

    @Override
    public String packageName() {
        return packageName;
    }

    @Override
    public TypeMirror importOperation() {
        return importOperation;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setImportOperation(TypeMirror importOperation) {
        this.importOperation = importOperation;
    }
}
