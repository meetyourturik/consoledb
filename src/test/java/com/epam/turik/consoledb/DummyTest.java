package com.epam.turik.consoledb;

import com.epam.turik.consoledb.application.PeopleWorker;
import com.epam.turik.consoledb.application.Settings;
import com.epam.turik.consoledb.data.PersonRepository;
import com.epam.turik.consoledb.data.objects.Person;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.ClassRule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

@Slf4j
@Testcontainers
@ActiveProfiles("test")
@SpringBootTest
class DummyTest {

	@ClassRule
	String[] names = {"Petya", "Vasya", "Ilya", "Nastya", "Katya", "Pasha", "Lenya", "Jenya"};

	@ClassRule
	Random r = new Random();

	@Autowired
	PeopleWorker worker;

	@Autowired
	PersonRepository personRepository;

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

	@SneakyThrows
	@Test
	void test() {
		TemporaryFolder temporaryFolder = new TemporaryFolder();
		temporaryFolder.create();
		File peopleFile = temporaryFolder.newFile("people.json");
		when(settings.getFilePath()).thenReturn(peopleFile.getAbsolutePath());
		Person[] people = generatePeople();
		writeToFile(peopleFile, people);

		worker.doWork();
		List<Person> peopleList = new ArrayList<>();
		personRepository.findAll().forEach(peopleList::add);
		Person[] newPeople = peopleList.toArray(new Person[0]);

		Assertions.assertEquals(newPeople.length, people.length);
		for (int i = 0; i < newPeople.length; i++) {
			Assertions.assertEquals(newPeople[i].getName(), people[i].getName());
			Assertions.assertEquals(newPeople[i].getAge(), people[i].getAge());
			Assertions.assertEquals(newPeople[i].getHascat(), people[i].getHascat());
			log.warn(newPeople[i].toString());
		}
	}

	@SneakyThrows
	void writeToFile(File file, Person[] people) {
		FileUtils.writeStringToFile(file, generatePeopleFile(people));
	}

	private String generatePeopleFile(Person[] people) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Person p : people) {
			sb.append("{\"name\":\"")
					.append(p.getName())
					.append("\",\"age\":")
					.append(p.getAge())
					.append(",\"hascat\":")
					.append(p.getHascat())
					.append("},");
		}
		return sb.substring(0, sb.length() - 1) + "]";
	}

	private Person[] generatePeople() {
		int n = r.nextInt(15) + 1;
		Person[] result = new Person[n];
		for (int i = 0; i <n; i++) {
			result[i] = randomPerson();
		}
		return result;
	}

	private Person randomPerson() {
		Person person = new Person();
		person.setName(names[r.nextInt(names.length)]);
		person.setAge(r.nextInt(17) + 17);
		person.setHascat(r.nextBoolean());
		return person;
	}

}
