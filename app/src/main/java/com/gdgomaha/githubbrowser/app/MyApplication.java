package com.gdgomaha.githubbrowser.app;

import com.gdgomaha.githubbrowser.BuildConfig;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public final class MyApplication extends DaggerApplication {

    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
}
