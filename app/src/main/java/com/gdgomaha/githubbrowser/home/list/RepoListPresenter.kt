package com.gdgomaha.githubbrowser.home.list

import com.gdgomaha.githubbrowser.data.RepoRepository
import com.gdgomaha.githubbrowser.di.ForScreen
import com.gdgomaha.githubbrowser.di.ScreenScope
import com.gdgomaha.githubbrowser.home.Navigator
import com.gdgomaha.githubbrowser.model.Repo
import com.gdgomaha.githubbrowser.utils.DisposableManager
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@ScreenScope class RepoListPresenter @Inject constructor(
        private val navigator: Navigator,
        @ForScreen disposableManager: DisposableManager,
        repoRepository: RepoRepository,
        viewModel: RepoListViewModel) {

    init {
        disposableManager.add(repoRepository.trendingRepos
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repos, error -> viewModel.processRepos(repos, error) }))
    }

    val selectedListener = fun (repo: Repo) {
        navigator.goToDetails(repo)
    }
}
