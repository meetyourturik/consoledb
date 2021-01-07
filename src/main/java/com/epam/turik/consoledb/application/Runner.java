package com.epam.turik.consoledb.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private PeopleWorker peopleWorker;

    @Override
    public void run(String... args) {
        peopleWorker.doWork();
    }
}
