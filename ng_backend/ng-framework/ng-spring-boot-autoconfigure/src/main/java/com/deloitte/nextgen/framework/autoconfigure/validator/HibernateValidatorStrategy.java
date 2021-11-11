package com.deloitte.nextgen.framework.autoconfigure.validator;

import javax.validation.Configuration;
import java.util.function.Consumer;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@FunctionalInterface
public interface HibernateValidatorStrategy extends Consumer<Configuration> {
}
