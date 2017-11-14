package com.gdgomaha.githubbrowser.app;

import com.gdgomaha.githubbrowser.BuildConfig;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class MyApplication extends DaggerApplication {

    protected ApplicationComponent component;

    @Override public void onCreate() {
        component = initComponent();
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent initComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return component;
    }
}
