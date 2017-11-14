package com.gdgomaha.githubbrowser.data;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestServiceModule {

    @Binds
    abstract RepoService provideRepoService(TestRepoService service);
}
