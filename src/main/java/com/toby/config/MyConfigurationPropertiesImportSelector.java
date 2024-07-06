package com.toby.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;

public class MyConfigurationPropertiesImportSelector implements DeferredImportSelector {

	@Override
	public String[] selectImports(final AnnotationMetadata importingClassMetadata) {
		final MultiValueMap<String, Object> attrs
			= importingClassMetadata.getAllAnnotationAttributes(EnableMyConfigurationProperties.class.getName());
		final Class propertyClass = (Class)attrs.getFirst("value");
		return new String[] {propertyClass.getName()};
	}

}
