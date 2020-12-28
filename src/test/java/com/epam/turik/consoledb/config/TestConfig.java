package com.epam.turik.consoledb.config;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Primary
    @Bean
    ApplicationArguments getArgs() {
        return new DefaultApplicationArguments("--file=./files/people.json");
    }
}
