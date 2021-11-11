package com.deloitte.nextgen.framework.automate.spi;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Global annotation processor context
 *
 * @author nishmehta
 * @since 22/07/2021 11:37 AM
 */
public interface AnnotationProcessingEnvironment {

    /**
     * Returns an implementation of some utility methods for
     * operating on elements
     *
     * @return element utilities
     */
    Elements getElementUtils();

    /**
     * Returns an implementation of some utility methods for
     * operating on types.
     *
     * @return type utilities
     */
    Types getTypeUtils();

    Filer getFiler();

    Messager getMessager();

    boolean isVerbose();

    void addProcessed(String name);

    boolean isProcessed(String name);

    String packageName();

    TypeMirror importOperation();
}
