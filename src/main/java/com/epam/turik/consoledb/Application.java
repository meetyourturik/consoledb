package com.epam.turik.consoledb;

import com.epam.turik.consoledb.application.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private Worker worker;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		try {
			worker.doWork();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}
