package com.gdgomaha.githubbrowser.home.detail;

import com.gdgomaha.githubbrowser.data.RepoRepository;
import com.gdgomaha.githubbrowser.di.ForScreen;
import com.gdgomaha.githubbrowser.di.ScreenScope;
import com.gdgomaha.githubbrowser.utils.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@ScreenScope final class RepoDetailPresenter {

    @Inject RepoDetailPresenter(
            @Named("owner") String owner,
            @Named("repo") String repoName,
            RepoRepository repoRepository,
            @ForScreen DisposableManager disposableManager,
            RepoDetailViewModel viewModel
    ) {
        disposableManager.add(repoRepository.getRepo(owner, repoName)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(viewModel::processRepo)
                .observeOn(Schedulers.io())
                .flatMap(repo -> repoRepository.getContributors(repo.getContributorsUrl()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewModel::processContributors));
    }
}
