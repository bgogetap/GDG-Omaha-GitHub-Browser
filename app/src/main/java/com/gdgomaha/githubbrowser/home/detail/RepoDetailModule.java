package com.gdgomaha.githubbrowser.home.detail;

import com.gdgomaha.githubbrowser.di.ScreenModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = ScreenModule.class)
public final class RepoDetailModule {

    private final String owner;
    private final String repoName;

    RepoDetailModule(String owner, String repoName) {
        this.owner = owner;
        this.repoName = repoName;
    }

    @Provides
    @Named("owner")
    String provideOwner() {
        return owner;
    }

    @Provides
    @Named("repo")
    String provideRepoName() {
        return repoName;
    }
}
