package com.epam.turik.consoledb.application;

import com.epam.turik.consoledb.model.PeopleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleWorker {
    @Autowired
    PeopleService service;

    @Autowired
    Settings settings;

    @SneakyThrows
    public void doWork() {
        String path = settings.getFilePath();
        service.saveData(path);
    }

}
