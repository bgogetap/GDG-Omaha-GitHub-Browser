package com.gdgomaha.githubbrowser.networking;

import com.gdgomaha.githubbrowser.model.adapters.ZonedDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.threeten.bp.ZonedDateTime;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public final class NetworkModule {

    private static final String BASE_URL = "https://api.github.com/";

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeAdapter())
                .create();
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .baseUrl(BASE_URL)
                .build();
    }
}
