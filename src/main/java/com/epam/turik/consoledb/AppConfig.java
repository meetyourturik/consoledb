package com.epam.turik.consoledb;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .driverClassName("org.postgresql.Driver")
            .url("jdbc:postgresql://localhost/test_db")
            .username("developer")
            .password("password")
            .build();
    }

    @Bean(initMethod="migrate")
    public Flyway flyway() {
        FluentConfiguration configuration = new FluentConfiguration()
            .locations("classpath:db/migration")
            .baselineOnMigrate(false)
//            .schemas("public")
            .dataSource(dataSource());
        return new Flyway(configuration);
    }
}
