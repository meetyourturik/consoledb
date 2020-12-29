package com.epam.turik.consoledb.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {

    @Primary
    @Bean
    ApplicationArguments getArgs() {
        return new DefaultApplicationArguments("--file=./files/people.json");
    }

}
