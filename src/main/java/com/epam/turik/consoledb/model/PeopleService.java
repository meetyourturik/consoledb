package com.epam.turik.consoledb.model;

import com.epam.turik.consoledb.data.PersonRepository;
import com.epam.turik.consoledb.data.objects.Person;
import com.epam.turik.consoledb.services.FileService;
import com.epam.turik.consoledb.services.JsonService;
import com.epam.turik.consoledb.model.objects.SaveResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class PeopleService {
    @Autowired
    private FileService fileManager;
    @Autowired
    private JsonService jsonManager;
    @Autowired
    private PersonRepository personRepository;

    public SaveResult saveData(String filePath) {
        try {
            String fileData = fileManager.readFile(filePath);
            Person[] people = jsonManager.parseJson(fileData, Person[].class);
            personRepository.saveAll(Arrays.asList(people));
            return SaveResult.SUCCESS;
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage());
            return SaveResult.ERROR;
        }
    }
}
