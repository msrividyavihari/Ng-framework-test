package com.deloitte.nextgen.framework.validator.annotations;

/**
 * @author nishmehta
 * @since 1.0.0
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Text {

    TextType type() default TextType.ALPHA_NUMERIC_SPACE_SPECIAL;

    char[] allowSpecialCharacters() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    enum TextType {
        ALPHA,
        ALPHA_SPACE,
        NUMERIC,
        NUMERIC_SPACE,
        ALPHA_NUMERIC,
        ALPHA_NUMERIC_SPACE,
        SPECIAL,
        ALPHA_NUMERIC_SPACE_SPECIAL
    }

    /**
     * Provide a regex to validate.
     *
     * <b>NOTE:</b> Regex will take precedence if provided.
     *
     * e.g. [A-Za-z0-9]+
     * @return
     */
    String regex() default "";

    String message() default "{com.deloitte.nextgen.framework.validator.annotations.Text.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
