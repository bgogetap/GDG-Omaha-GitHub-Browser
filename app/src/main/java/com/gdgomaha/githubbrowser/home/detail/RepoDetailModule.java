package com.gdgomaha.githubbrowser.home.detail;

import com.gdgomaha.githubbrowser.di.ScreenModule;
import com.gdgomaha.githubbrowser.di.ScreenScope;
import com.jakewharton.rxrelay2.PublishRelay;

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

    @Provides
    @ScreenScope
    PublishRelay<Boolean> provideStarsUpdatedRelay() {
        return PublishRelay.create();
    }
}
