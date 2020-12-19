package com.epam.turik.consoledb.data;

import org.springframework.stereotype.Component;
import java.io.*;

@Component
public class FileManager {
    public String readFile(String filePath) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
            return sb.toString();
        }
    }
}