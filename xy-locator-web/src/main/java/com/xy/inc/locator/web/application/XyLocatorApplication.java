package com.xy.inc.locator.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.xy.inc.locator"
})
//@EntityScan("com.xy.inc.locator.model")
//@EnableJpaRepositories("com.xy.inc.locator.repository")
public class XyLocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyLocatorApplication.class, args);
    }

}
