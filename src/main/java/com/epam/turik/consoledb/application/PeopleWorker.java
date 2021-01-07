package com.epam.turik.consoledb.application;

import com.epam.turik.consoledb.model.PeopleService;
import com.epam.turik.consoledb.model.objects.SaveResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
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
            log.debug("success");
        } else {
            log.warn("not success");
        }
    }
}
