package com.epam.turik.consoledb;

import com.epam.turik.consoledb.model.person.Person;
import com.epam.turik.consoledb.model.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleDbApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(ConsoleDbApplication.class);
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		log.info("start");
		SpringApplication.run(ConsoleDbApplication.class, args);
		log.info("finish");
	}
	@Override
	public void run(String... args) {
		personRepository.save(new Person("ilyusha", 12, false));
		for (Person p: personRepository.findAll()) {
			log.info(p.toString());
		}
	}
}
