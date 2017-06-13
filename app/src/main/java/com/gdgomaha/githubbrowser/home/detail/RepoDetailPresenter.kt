package com.gdgomaha.githubbrowser.home.detail

import com.gdgomaha.githubbrowser.data.RepoRepository
import com.gdgomaha.githubbrowser.di.ForScreen
import com.gdgomaha.githubbrowser.di.ScreenScope
import com.gdgomaha.githubbrowser.model.Contributor
import com.gdgomaha.githubbrowser.utils.DisposableManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@ScreenScope internal class RepoDetailPresenter @Inject constructor(
        @Named("owner") owner: String,
        @Named("repo") repoName: String,
        repoRepository: RepoRepository,
        @ForScreen disposableManager: DisposableManager,
        viewModel: RepoDetailViewModel
) {

    init {
        disposableManager.add(repoRepository.getRepo(owner, repoName)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess({ viewModel.processRepo(it) })
                .observeOn(Schedulers.io())
                .flatMap<List<Contributor>> { (_, _, _, _, _, _, contributorsUrl) -> repoRepository.getContributors(contributorsUrl) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ contributors, error -> viewModel.processContributors(contributors, error) }))
    }
}
