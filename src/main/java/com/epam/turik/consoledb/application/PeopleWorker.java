package com.epam.turik.consoledb.application;

import com.epam.turik.consoledb.model.PeopleService;
import com.epam.turik.consoledb.model.objects.SaveResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleWorker {
    @Autowired
    PeopleService peopleService;

    @Autowired
    Settings settings;

    @SneakyThrows
    public void doWork() {
        String path = settings.getFilePath();
        SaveResult result = peopleService.saveData(path);
        if (result == SaveResult.SUCCESS) {
            System.out.println("success");
        } else {
            System.out.println("not success");
        }
    }

}
