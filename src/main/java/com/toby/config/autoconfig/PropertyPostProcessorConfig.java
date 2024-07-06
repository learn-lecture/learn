package com.toby.config.autoconfig;

import static org.springframework.core.annotation.AnnotationUtils.*;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;

import com.toby.config.MyAutoConfiguration;
import com.toby.config.MyConfigurationProperties;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {

	@Bean
	static BeanPostProcessor propertyPostProcessor(final Environment environment) {
		return new BeanPostProcessor() {
			@Override
			public Object postProcessAfterInitialization(
				final Object bean,
				final String beanName
			) throws BeansException {
				final MyConfigurationProperties annotation = findAnnotation(bean.getClass(), MyConfigurationProperties.class);
				if (annotation == null) {
					return bean;
				}

				final Map<String, Object> attrs = getAnnotationAttributes(annotation);
				final String prefix = (String)attrs.get("prefix");

				return Binder.get(environment).bindOrCreate(prefix, bean.getClass());
			}
		};
	}

}
