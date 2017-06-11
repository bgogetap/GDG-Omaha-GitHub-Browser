package com.gdgomaha.githubbrowser.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
final class ApplicationModule {

    private final Application application;

    ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Reusable
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
