package com.gdgomaha.githubbrowser.home.list;

import com.gdgomaha.githubbrowser.data.RepoRepository;
import com.gdgomaha.githubbrowser.di.ForScreen;
import com.gdgomaha.githubbrowser.di.ScreenScope;
import com.gdgomaha.githubbrowser.home.Navigator;
import com.gdgomaha.githubbrowser.model.Repo;
import com.gdgomaha.githubbrowser.utils.DisposableManager;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@ScreenScope public final class RepoListPresenter {

    private final Navigator navigator;

    @Inject RepoListPresenter(
            Navigator navigator,
            @ForScreen DisposableManager disposableManager,
            RepoRepository repoRepository,
            RepoListViewModel viewModel) {
        this.navigator = navigator;
        disposableManager.add(repoRepository.getTrendingRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewModel::processRepos));
    }

    void repoSelected(Repo repo) {
        navigator.goToDetails(repo);
    }
}
