package com.deloitte.nextgen.framework.autoconfigure.validator;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Configuration;
import javax.validation.ConstraintValidator;
import java.util.List;

/**
 * @author nishmehta
 * @since 1.0.0
 */

@Slf4j
public class DefaultLocalValidatorFactoryBean extends LocalValidatorFactoryBean {

    private HibernateValidatorStrategy hibernateValidatorStrategy;
    private List<? extends ConstraintValidator<?, ?>> validators;

    public DefaultLocalValidatorFactoryBean(HibernateValidatorStrategy hibernateValidatorStrategy,
                                            List<? extends ConstraintValidator<?, ?>> validators) {
        this.hibernateValidatorStrategy = hibernateValidatorStrategy;
        this.validators = validators;
    }

    @Override
    protected void postProcessConfiguration(Configuration<?> configuration) {
        super.postProcessConfiguration(configuration);
        HibernateValidatorConfiguration hibernateConfiguration = (HibernateValidatorConfiguration) configuration;
        hibernateValidatorStrategy.accept(hibernateConfiguration);
    }

}