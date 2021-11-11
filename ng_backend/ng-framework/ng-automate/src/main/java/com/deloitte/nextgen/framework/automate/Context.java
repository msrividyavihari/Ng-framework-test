package com.deloitte.nextgen.framework.automate;

import com.deloitte.nextgen.framework.automate.spi.AnnotationProcessingEnvironment;

/**
 * This class act as local context for the each round of annotation processing
 *
 * @author nishmehta
 * @since 03/08/2021 11:55 AM
 */
public class Context {
    private final AnnotationProcessingEnvironment processorContext;

    public Context(AnnotationProcessingEnvironment processorContext) {
        this.processorContext = processorContext;
    }

    public AnnotationProcessingEnvironment getProcessorContext() {
        return processorContext;
    }
}

