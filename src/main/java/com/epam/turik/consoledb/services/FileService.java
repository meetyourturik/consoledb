package com.epam.turik.consoledb.services;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class FileService {
    public String readFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
            return sb.toString();
        }
    }
}