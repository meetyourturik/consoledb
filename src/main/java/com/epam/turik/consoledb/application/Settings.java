package com.epam.turik.consoledb.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class Settings {
    private final String filePath;

    @Autowired
    Settings(ApplicationArguments args) {
        if (args.containsOption("file")) {
            this.filePath = args.getOptionValues("file").get(0);
        } else {
            throw new RuntimeException("no filepath specified");
        }
    }

    public String getFilePath() {
        return filePath;
    }
}
