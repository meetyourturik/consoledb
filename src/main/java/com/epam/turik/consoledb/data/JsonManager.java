package com.epam.turik.consoledb.data;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class JsonManager {
    private final Gson gson = new Gson();

    public <T> T parseJson(String json, Class<T> clazz) {
        T object;
        object = gson.fromJson(json, clazz);
        return object;
    }
}
