package com.deloitte.nextgen.framework.autoconfigure.validator;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author nishmehta
 * @since 1.0.0
 */
public class OverridePrimaryValidatorProcessor implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    /**
     * The bean name of the auto-configured Validator.
     */
    private static final String VALIDATOR_BEAN_NAME = "validator";

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        }
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition definition = getAutoConfiguredValidator(registry);
        disablePrimarySpringValidator();
        if (definition != null) {
            definition.setPrimary(true);
        }
    }

    private BeanDefinition getAutoConfiguredValidator(BeanDefinitionRegistry registry) {
        if (registry.containsBeanDefinition(VALIDATOR_BEAN_NAME)) {
            BeanDefinition definition = registry.getBeanDefinition(VALIDATOR_BEAN_NAME);
            if (isTypeMatch(VALIDATOR_BEAN_NAME, LocalValidatorFactoryBean.class)) {
                return definition;
            }
        }
        return null;
    }

    private boolean isTypeMatch(String name, Class<?> type) {
        return beanFactory != null && beanFactory.isTypeMatch(name, type);
    }

    private void disablePrimarySpringValidator() {

        String[] validatorBeans = beanFactory.getBeanNamesForType(Validator.class, false, false);
        for (String validatorBean : validatorBeans) {
            BeanDefinition definition = beanFactory.getBeanDefinition(validatorBean);
            if (!validatorBean.equals(VALIDATOR_BEAN_NAME)) {
                definition.setPrimary(false);
            }
        }
    }
}
