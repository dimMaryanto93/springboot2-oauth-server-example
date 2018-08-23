package com.tabeldata.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcApplication {

    @Bean
    public JdbcTemplate jdbcTemplate(
            @Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate paramsJdbcTemplate(
            @Qualifier("dataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
