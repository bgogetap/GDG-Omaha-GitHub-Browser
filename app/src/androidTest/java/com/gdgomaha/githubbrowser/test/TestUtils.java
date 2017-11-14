package com.gdgomaha.githubbrowser.test;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.inject.Inject;

public class TestUtils {

    private final Gson gson;

    @Inject
    TestUtils(Gson gson) {
        this.gson = gson;
    }

    public <T> T loadJson(String path, Class<T> clazz) {
        return gson.fromJson(jsonForFile(path), clazz);
    }

    private String jsonForFile(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream(path)));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not read from resource at: " + path, e);
        }
    }
}
