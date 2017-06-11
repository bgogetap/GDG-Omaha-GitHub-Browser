package com.gdgomaha.githubbrowser.test;

import com.gdgomaha.githubbrowser.model.adapters.ZonedDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.threeten.bp.ZonedDateTime;

public class GsonHelper {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
            .create();

    private GsonHelper() {

    }
}
