package com.inc.xy.locator.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {LiquibaseAutoConfiguration.class})
@ComponentScan(basePackages = {"com.inc.xy.locator"})
@EntityScan("com.inc.xy.locator.model")
@EnableJpaRepositories("com.inc.xy.locator.repository")
public class XyLocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyLocatorApplication.class, args);
    }

}
