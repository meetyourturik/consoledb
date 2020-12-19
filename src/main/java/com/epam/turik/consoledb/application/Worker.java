package com.epam.turik.consoledb.application;

import com.epam.turik.consoledb.data.FileManager;
import com.epam.turik.consoledb.data.JsonManager;
import com.epam.turik.consoledb.model.person.Person;
import com.epam.turik.consoledb.model.person.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class Worker {
    @Autowired
    private Settings settings;
    @Autowired
    private FileManager fileManager;
    @Autowired
    private JsonManager jsonManager;
    @Autowired
    private PersonRepository personRepository;

    public void doWork() throws IOException {
        String fileData = fileManager.readFile(settings.getFilePath());
        Person[] people = jsonManager.parseJson(fileData, Person[].class);
        personRepository.saveAll(Arrays.asList(people));
    }
}
