package com.example.trade.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseConfiguration {
	
	@Value("${spring.trade.driverClassName}")
	private String driverClassName;
	@Value("${spring.trade.url}")
	private String url;
	@Value("${spring.trade.username}")
	private String username;
	@Value("${spring.trade.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;

	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
	      return new JdbcTemplate(dataSource);
	}
}
