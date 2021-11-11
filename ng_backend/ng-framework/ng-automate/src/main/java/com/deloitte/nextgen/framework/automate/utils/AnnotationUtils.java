package com.deloitte.nextgen.framework.automate.utils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import java.util.Map;

/**
 * @author nishmehta
 * @since 04/08/2021 11:49 AM
 */
public class AnnotationUtils {

    public static final String UTILITY_CLASS = "Utility class";

    private AnnotationUtils() {
        throw new IllegalStateException(UTILITY_CLASS);
    }

    public static AnnotationValue getAnnotationValueOfAttribute(AnnotationMirror annotationMirror) {
        return getAnnotationValueOfAttribute(annotationMirror, "value");
    }

    public static AnnotationValue getAnnotationValueOfAttribute(AnnotationMirror annotationMirror, String key) {
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues().entrySet()) {
            if (entry.getKey().getSimpleName().toString().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
