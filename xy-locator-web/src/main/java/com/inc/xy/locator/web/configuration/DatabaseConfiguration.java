package com.inc.xy.locator.web.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() throws SQLException {

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath*:db/changelog-master.xml");
        return liquibase;
    }

}


