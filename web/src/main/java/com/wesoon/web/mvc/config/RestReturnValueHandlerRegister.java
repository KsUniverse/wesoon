package com.wesoon.web.mvc.config;

import com.wesoon.web.mvc.annotation.EnableRESTful;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @Description
 * @Author WangXingYu
 * @Data 2018/11/14
 * @Version 1.0
 */
@Configuration
public class RestReturnValueHandlerRegister implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(EnableRESTful.class.getName()));
        if(mapperScanAttrs != null) {
            registerBeanDefinitions(importingClassMetadata, mapperScanAttrs, registry, RestReturnValueHandler.class.getName());
        }
    }

    private void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, AnnotationAttributes mapperScanAttrs, BeanDefinitionRegistry registry, String beanName) {
        String basePackage = mapperScanAttrs.getString("controllerBasePackage");
        if(!StringUtils.hasText(basePackage)) {
            basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        }
        BeanDefinitionBuilder beanDefinitionBuilder =  BeanDefinitionBuilder.genericBeanDefinition(RestReturnValueHandler.class);
        beanDefinitionBuilder.addConstructorArgValue(basePackage);
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }
}
