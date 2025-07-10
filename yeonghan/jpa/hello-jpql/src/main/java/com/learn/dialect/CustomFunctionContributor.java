package com.learn.dialect;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.BasicType;
import org.hibernate.type.BasicTypeRegistry;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.spi.TypeConfiguration;

public class CustomFunctionContributor implements FunctionContributor {
    @Override
    public void contributeFunctions(FunctionContributions functionContributions) {
        TypeConfiguration typeConfiguration = functionContributions.getTypeConfiguration();
        BasicTypeRegistry basicTypeRegistry = typeConfiguration.getBasicTypeRegistry();
        BasicType<String> stringBasicType = basicTypeRegistry.resolve(StandardBasicTypes.STRING);
        functionContributions.getFunctionRegistry()
            .registerPattern("my_function", "my_function(?1)", stringBasicType);
    }
}