package com.epam.turik.consoledb.services;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class JsonService {
    private final Gson gson = new Gson();

    public <T> T parseJson(String json, Class<T> clazz) {
        T object;
        object = gson.fromJson(json, clazz);
        return object;
    }
}
