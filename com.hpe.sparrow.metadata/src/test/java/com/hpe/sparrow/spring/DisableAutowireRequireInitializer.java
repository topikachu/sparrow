package com.hpe.sparrow.spring;

/**
 * Created by gongy on 2017/1/25.
 */

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.support.GenericApplicationContext;

public class DisableAutowireRequireInitializer
        implements ApplicationContextInitializer<GenericApplicationContext> {
    @Override
    public void initialize(GenericApplicationContext ctx) {
        ctx.registerBeanDefinition(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME,
                BeanDefinitionBuilder
                        .rootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class)
                        .addPropertyValue("requiredParameterValue", false)
                        .getBeanDefinition());
    }
}
