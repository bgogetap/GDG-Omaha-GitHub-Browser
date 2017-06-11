package com.gdgomaha.githubbrowser.home;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.di.ActivityScope;
import com.gdgomaha.githubbrowser.home.detail.RepoDetailFragment;
import com.gdgomaha.githubbrowser.model.Repo;

import javax.inject.Inject;

@ActivityScope
public final class Navigator {

    private final MainActivity activity;

    @Inject
    Navigator(MainActivity activity) {
        this.activity = activity;
    }

    public void goToDetails(Repo repo) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, RepoDetailFragment.newInstance(repo))
                .addToBackStack(null)
                .commit();
    }
}
