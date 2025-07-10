package com.learn.dialect;

import java.util.Date;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.CurrentFunction;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.BasicType;
import org.hibernate.type.BasicTypeRegistry;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.spi.TypeConfiguration;

// Hiber 6.x, 비추천
// yml 혹은 properties에 해당 방언 등록
public class MyH2Dialect extends H2Dialect {
	@Override
	public void initializeFunctionRegistry(FunctionContributions functionContributions) {
		super.initializeFunctionRegistry(functionContributions);

		TypeConfiguration typeConfiguration = functionContributions.getTypeConfiguration();
		functionContributions.getFunctionRegistry()
			.registerPattern(
				"my_function",
				"my_function(?1)",
				typeConfiguration.getBasicTypeForJavaType(String.class)
			);

		// 아래는 Hibernate 에서 기본으로 처리하는 예시
		// package org.hibernate.dialect; 에서 확인 가능
		BasicTypeRegistry basicTypeRegistry = typeConfiguration.getBasicTypeRegistry();
		BasicType<Date> timestampType = basicTypeRegistry.resolve(StandardBasicTypes.TIMESTAMP);
		functionContributions.getFunctionRegistry().register(
			"current_timestamp",
			new CurrentFunction("current_timestamp", this.currentTimestamp(), timestampType));

	}
}
