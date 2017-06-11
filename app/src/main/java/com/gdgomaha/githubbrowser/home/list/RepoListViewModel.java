package com.gdgomaha.githubbrowser.home.list;

import android.view.View;

import com.gdgomaha.githubbrowser.di.ScreenScope;
import com.gdgomaha.githubbrowser.model.Repo;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

@ScreenScope
public final class RepoListViewModel {

    private final BehaviorRelay<List<Repo>> repoRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> listVisibilityRelay = BehaviorRelay.createDefault(View.GONE);
    private final BehaviorRelay<Integer> loadingVisibilityRelay = BehaviorRelay.createDefault(View.VISIBLE);

    @Inject RepoListViewModel() {
    }

    void processRepos(List<Repo> repos, Throwable error) {
        listVisibilityRelay.accept(View.VISIBLE);
        loadingVisibilityRelay.accept(View.GONE);
        if (error == null) {
            repoRelay.accept(repos);
        } else {
            Timber.e(error, "Error loading repos");
        }
    }

    Observable<List<Repo>> repos() {
        return repoRelay;
    }

    Observable<Integer> listVisibility() {
        return listVisibilityRelay;
    }

    Observable<Integer> loadingVisibility() {
        return loadingVisibilityRelay;
    }
}
