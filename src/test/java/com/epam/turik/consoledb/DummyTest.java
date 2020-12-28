package com.epam.turik.consoledb;

import com.epam.turik.consoledb.config.TestConfig;
import com.epam.turik.consoledb.data.PersonRepository;
import com.epam.turik.consoledb.data.objects.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(classes = TestConfig.class)
class DummyTest {
	@Autowired
	ApplicationArguments args;

	@Autowired
	PersonRepository personRepository;

	@Container
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:10.14")
			.withDatabaseName("my_db")
			.withUsername("developer")
			.withPassword("password");

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
	}

	@Test
	void test() {
		for (Person p : personRepository.findAll()) {
			System.out.println(p);
		}
	}
}
