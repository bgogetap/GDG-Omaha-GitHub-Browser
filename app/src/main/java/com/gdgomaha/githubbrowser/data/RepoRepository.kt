package com.gdgomaha.githubbrowser.data

import com.gdgomaha.githubbrowser.model.Contributor
import com.gdgomaha.githubbrowser.model.Repo
import io.reactivex.Maybe
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class RepoRepository @Inject constructor(private val service: RepoService) {

    private var cachedTrendingRepos: List<Repo>? = null
    private val repoCache = LinkedHashMap<String, MutableList<Repo>>()
    private val contributorsCache = LinkedHashMap<String, List<Contributor>>()

    fun getTrendingRepos(): Single<List<Repo>> = Maybe.concat(cachedTrending(), apiTrending().toMaybe()).firstOrError()

    fun getRepo(owner: String, repoName: String): Single<Repo> {
        return Maybe.concat(cachedIndividual(owner, repoName), apiIndividual(owner, repoName).toMaybe())
                .firstOrError()
    }

    fun getContributors(url: String): Single<List<Contributor>> {
        return Maybe.concat(cachedContributors(url), apiContributors(url).toMaybe())
                .firstOrError()
    }

    private fun cachedTrending(): Maybe<List<Repo>> {
        return Maybe.create<List<Repo>> { e ->
            cachedTrendingRepos?.let {
                e.onSuccess(it)
            } ?: e.onComplete()
        }
    }

    private fun apiTrending(): Single<List<Repo>> {
        return service.recentTrendingRepos
                .map { it.repos }
                .doOnSuccess({ this.refreshMap(it) })
                .doOnSuccess { this.cachedTrendingRepos = it }
    }

    private fun cachedIndividual(owner: String, repoName: String): Maybe<Repo> {
        return Maybe.create<Repo> { e ->
            repoCache[owner]?.firstOrNull { it.name == repoName }?.let {
                e.onSuccess(it)
                e.onComplete()
            } ?: e.onComplete()
        }
    }

    private fun apiIndividual(owner: String, repoName: String): Single<Repo> {
        return service.getRepo(owner, repoName)
                .doOnSuccess({ this.addToMap(it) })
    }

    private fun cachedContributors(url: String): Maybe<List<Contributor>> {
        return Maybe.create<List<Contributor>> { e ->
            contributorsCache[url]?.let {
                e.onSuccess(it)
                e.onComplete()
            } ?: e.onComplete()
        }
    }

    private fun apiContributors(url: String): Single<List<Contributor>> {
        return service.getContributors(url)
                .doOnSuccess { contributors -> contributorsCache.put(url, contributors) }
    }

    private fun refreshMap(repos: List<Repo>) {
        repoCache.clear()
        repos.forEach {
            addToMap(it)
        }
    }

    private fun addToMap(repo: Repo) {
        val reposForOwner = repoCache[repo.owner.login] ?: ArrayList()
        reposForOwner.add(repo)
        repoCache.put(repo.owner.login, reposForOwner)
    }
}
