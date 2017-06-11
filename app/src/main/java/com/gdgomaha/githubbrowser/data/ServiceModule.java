package com.gdgomaha.githubbrowser.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class ServiceModule {

    @Provides
    @Singleton
    static RepoService provideRepoService(Retrofit retrofit) {
        return retrofit.create(RepoService.class);
    }
}
