package com.toby.spring.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.toby.spring.Application;

@HelloBootTest
public class DataSourceTest {

	@Autowired DataSource dataSource;

	@Test
	void connect() throws SQLException {
		final Connection connection = dataSource.getConnection();
		connection.close();
	}

}
