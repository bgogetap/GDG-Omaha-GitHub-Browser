package com.gdgomaha.githubbrowser.app;

import com.gdgomaha.githubbrowser.data.TestServiceModule;
import com.gdgomaha.githubbrowser.di.ActivityBindingModule;
import com.gdgomaha.githubbrowser.home.list.RepoListFragmentTest;
import com.gdgomaha.githubbrowser.networking.TestNetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class,
        TestServiceModule.class,
        TestNetworkModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(RepoListFragmentTest repoListFragmentTest);
}
