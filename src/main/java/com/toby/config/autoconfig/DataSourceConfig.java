package com.toby.config.autoconfig;

import java.sql.Driver;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.toby.config.ConditionalMyOnClass;
import com.toby.config.EnableMyConfigurationProperties;
import com.toby.config.MyAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDatasourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfig {

	@Bean
	@ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
	@ConditionalOnMissingBean
	DataSource hikariDataSource(final MyDatasourceProperties properties) {
		final HikariDataSource dataSource = new HikariDataSource();

		dataSource.setDriverClassName(properties.getDriverClassName());
		dataSource.setJdbcUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());

		return dataSource;
	}

	@Bean
	@ConditionalOnMissingBean
	DataSource dataSource(final MyDatasourceProperties properties) throws ClassNotFoundException {
		final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass((Class<? extends Driver>)Class.forName(properties.getDriverClassName()));
		dataSource.setUrl(properties.getUrl());
		dataSource.setUsername(properties.getUsername());
		dataSource.setPassword(properties.getPassword());

		return dataSource;
	}

	@Bean
	@ConditionalOnSingleCandidate(DataSource.class)
	@ConditionalOnMissingBean
	JdbcTemplate jdbcTemplate(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@ConditionalOnSingleCandidate(DataSource.class)
	@ConditionalOnMissingBean
	JdbcTransactionManager jdbcTransactionManager(final DataSource dataSource) {
		return new JdbcTransactionManager(dataSource);
	}

}
