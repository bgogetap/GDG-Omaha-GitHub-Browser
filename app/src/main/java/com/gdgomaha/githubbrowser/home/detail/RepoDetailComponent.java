package com.gdgomaha.githubbrowser.home.detail;

import com.gdgomaha.githubbrowser.di.ScreenModule;
import com.gdgomaha.githubbrowser.di.ScreenScope;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = ScreenModule.class)
public interface RepoDetailComponent extends AndroidInjector<RepoDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoDetailFragment> {

        @BindsInstance
        public abstract void bindOwner(@Named("owner") String owner);

        @BindsInstance
        public abstract void bindRepoName(@Named("repo") String repo);

        @Override
        public void seedInstance(RepoDetailFragment repoDetailFragment) {
            bindOwner(repoDetailFragment.getArguments().getString(RepoDetailFragment.OWNER_KEY));
            bindRepoName(repoDetailFragment.getArguments().getString(RepoDetailFragment.REPO_KEY));
        }
    }
}
