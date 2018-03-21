package com.inc.xy.locator.web.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "com.inc.xy.locator"
})
//@EntityScan("com.xy.inc.locator.model")
//@EnableJpaRepositories("com.xy.inc.locator.repository")
public class XyLocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyLocatorApplication.class, args);
    }

}
