package com.gdgomaha.githubbrowser.home.detail;

import com.gdgomaha.githubbrowser.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = RepoDetailModule.class)
public interface RepoDetailComponent extends AndroidInjector<RepoDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoDetailFragment> {

        public abstract void module(RepoDetailModule module);

        @Override public void seedInstance(RepoDetailFragment repoDetailFragment) {
            module(new RepoDetailModule(
                    repoDetailFragment.getArguments().getString(RepoDetailFragment.OWNER_KEY),
                    repoDetailFragment.getArguments().getString(RepoDetailFragment.REPO_KEY)
            ));
        }
    }
}
