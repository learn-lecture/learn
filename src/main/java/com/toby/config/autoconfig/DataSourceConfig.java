package com.toby.config.autoconfig;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.toby.config.ConditionalMyOnClass;
import com.toby.config.EnableMyConfigurationProperties;
import com.toby.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDatasourceProperties.class)
public class DataSourceConfig {

	@Bean
	DataSource dataSource(final MyDatasourceProperties properties) throws ClassNotFoundException {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass((Class<? extends Driver>)Class.forName(properties.getDriverClassName()));
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());

		return dataSource;
	}

}
