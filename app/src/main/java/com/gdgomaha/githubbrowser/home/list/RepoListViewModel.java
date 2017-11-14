package com.gdgomaha.githubbrowser.home.list;

import android.view.View;

import com.gdgomaha.githubbrowser.R;
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
    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> listVisibilityRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> loadingVisibilityRelay = BehaviorRelay.create();

    @Inject
    RepoListViewModel() {
    }

    void processRepos(List<Repo> repos, Throwable error) {
        loadingVisibilityRelay.accept(View.GONE);
        if (error == null) {
            errorRelay.accept(-1);
            repoRelay.accept(repos);
            listVisibilityRelay.accept(View.VISIBLE);
        } else {
            Timber.e(error, "Error loading repos");
            errorRelay.accept(R.string.api_error_repos);
            listVisibilityRelay.accept(View.GONE);
        }
    }

    void toggleLoading() {
        loadingVisibilityRelay.accept(View.VISIBLE);
        listVisibilityRelay.accept(View.GONE);
        errorRelay.accept(-1);
    }

    Observable<List<Repo>> repos() {
        return repoRelay;
    }

    Observable<Integer> error() {
        return errorRelay;
    }

    Observable<Integer> listVisibility() {
        return listVisibilityRelay;
    }

    Observable<Integer> loadingVisibility() {
        return loadingVisibilityRelay;
    }
}
