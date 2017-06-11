package com.gdgomaha.githubbrowser.di;

import com.gdgomaha.githubbrowser.home.MainActivity;
import com.gdgomaha.githubbrowser.home.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
