package com.gdgomaha.githubbrowser.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JsonLoader {

    private static final JsonLoader INSTANCE = new JsonLoader();

    private JsonLoader() {

    }

    public static String forFile(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    INSTANCE.getClass().getClassLoader().getResourceAsStream(path)));
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
