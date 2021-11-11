package com.deloitte.nextgen.framework.validator.constraintvalidators;

import com.deloitte.nextgen.framework.commons.constants.PatternConstants;
import com.deloitte.nextgen.framework.validator.annotations.Text;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class TextValidator implements ConstraintValidator<Text, String> {

    private Text annotation;

    private Pattern pattern;


    @Override
    public void initialize(Text annotation) {
        this.annotation = annotation;
        if (StringUtils.isNotEmpty(annotation.regex())) {

            try {
                pattern = Pattern.compile(annotation.regex());
            } catch (PatternSyntaxException e) {
                log.error("Incorrect pattern", e);
                throw e;
            }
            return;
        }

        switch (annotation.type()) {
            case ALPHA:
                pattern = Pattern.compile(PatternConstants.Text.ALPHA);
                break;
            case ALPHA_SPACE:
                pattern = Pattern.compile(PatternConstants.Text.ALPHA_SPACE);
                break;
            case ALPHA_NUMERIC:
                pattern = Pattern.compile(PatternConstants.Text.ALPHA_NUMERIC);
                break;
            case ALPHA_NUMERIC_SPACE:
                pattern = Pattern.compile(PatternConstants.Text.ALPHA_NUMERIC_SPACE);
                break;
            case NUMERIC:
                pattern = Pattern.compile(PatternConstants.Text.NUMERIC);
                break;
            case NUMERIC_SPACE:
                pattern = Pattern.compile(PatternConstants.Text.NUMERIC_SPACE);
                break;
            case SPECIAL:
                pattern = Pattern.compile(PatternConstants.Text.SPECIAL);
                break;
            case ALPHA_NUMERIC_SPACE_SPECIAL:

                if (annotation.allowSpecialCharacters().length == 0) {
                    throw new IllegalArgumentException("Special characters must be mentioned when type is 'ALPHA_NUMERIC_SPACE_SPECIAL'");
                }

                String p = String.format("^[\\sA-Za-z0-9%s]+$", Arrays.toString(annotation.allowSpecialCharacters()));

                pattern = Pattern.compile(p);
                break;
            default:
                pattern = Pattern.compile(PatternConstants.Text.ALPHA_NUMERIC_SPACE_SPECIAL);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null)
            return true;

        log.debug("Pattern selected {}", pattern.toString());

        context.disableDefaultConstraintViolation();
        if (value.length() < annotation.min() || value.length() > annotation.max()) {
            context.buildConstraintViolationWithTemplate("text does not meet min or max requirement");
            return false;
        } else if (StringUtils.isNotEmpty(annotation.regex())) {
            context.buildConstraintViolationWithTemplate("text does not meet supplied regex requirement");
            return pattern.matcher(value).matches();
        } else {
            context.buildConstraintViolationWithTemplate("text does not meet supplied type requirement");
            return pattern.matcher(value).matches();
        }
    }
}
