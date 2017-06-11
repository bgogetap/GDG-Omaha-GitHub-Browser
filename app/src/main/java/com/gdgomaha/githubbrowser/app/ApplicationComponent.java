package com.gdgomaha.githubbrowser.app;

import com.gdgomaha.githubbrowser.data.ServiceModule;
import com.gdgomaha.githubbrowser.di.ActivityBindingModule;
import com.gdgomaha.githubbrowser.networking.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        NetworkModule.class,
        ServiceModule.class,
})
public interface ApplicationComponent extends AndroidInjector<MyApplication> {

}
