package com.epam.turik.consoledb;

import com.epam.turik.consoledb.application.PeopleWorker;
import com.epam.turik.consoledb.application.Settings;
import com.epam.turik.consoledb.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest(classes = TestConfig.class)
class DummyTest {
	@Autowired
	ApplicationArguments args;

	@Autowired
	PeopleWorker worker;

	@MockBean
	Settings settings;

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
		when(settings.getFilePath()).thenReturn("/my/path/to/data.json");

		worker.doWork();
	}
}
