package com.gdgomaha.githubbrowser.data;

import com.gdgomaha.githubbrowser.model.Contributor;
import com.gdgomaha.githubbrowser.model.Repo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public final class RepoRepository {

    private final RepoService service;

    private List<Repo> cachedTrendingRepos;
    private Map<String, List<Repo>> repoCache = new LinkedHashMap<>();
    private Map<String, List<Contributor>> contributorsCache = new LinkedHashMap<>();

    @Inject RepoRepository(RepoService service) {
        this.service = service;
    }

    public Single<List<Repo>> getTrendingRepos() {
        return Maybe.concat(cachedTrending(), apiTrending().toMaybe())
                .firstOrError();
    }

    public Single<Repo> getRepo(String owner, String repoName) {
        return Maybe.concat(cachedIndividual(owner, repoName), apiIndividual(owner, repoName).toMaybe())
                .firstOrError();
    }

    public Single<List<Contributor>> getContributors(String url) {
        return Maybe.concat(cachedContributors(url), apiContributors(url).toMaybe())
                .firstOrError();
    }

    private Maybe<List<Repo>> cachedTrending() {
        return Maybe.create(e -> {
            if (cachedTrendingRepos != null) {
                e.onSuccess(cachedTrendingRepos);
            } else {
                e.onComplete();
            }
        });
    }

    private Single<List<Repo>> apiTrending() {
        return service.getRecentTrendingRepos()
                .map(response -> response.repos)
                .doOnSuccess(this::refreshMap)
                .doOnSuccess(repos -> this.cachedTrendingRepos = repos);
    }

    private Maybe<Repo> cachedIndividual(String owner, String repoName) {
        return Maybe.create(e -> {
            List<Repo> reposForOwner = repoCache.get(owner);
            if (reposForOwner != null) {
                for (Repo repo : reposForOwner) {
                    if (repo.getName().equals(repoName)) {
                        e.onSuccess(repo);
                        e.onComplete();
                        return;
                    }
                }
            }
            e.onComplete();
        });
    }

    private Single<Repo> apiIndividual(String owner, String repoName) {
        return service.getRepo(owner, repoName)
                .doOnSuccess(this::addToMap);
    }

    private Maybe<List<Contributor>> cachedContributors(String url) {
        return Maybe.create(e -> {
            List<Contributor> contributors = contributorsCache.get(url);
            if (contributors != null) {
                e.onSuccess(contributors);
                e.onComplete();
                return;
            }
            e.onComplete();
        });
    }

    private Single<List<Contributor>> apiContributors(String url) {
        return service.getContributors(url)
                .doOnSuccess(contributors -> contributorsCache.put(url, contributors));
    }

    private void refreshMap(List<Repo> repos) {
        repoCache.clear();
        for (Repo repo : repos) {
            addToMap(repo);
        }
    }

    private void addToMap(Repo repo) {
        List<Repo> reposForOwner = repoCache.get(repo.getOwner().getLogin());
        if (reposForOwner == null) {
            reposForOwner = new ArrayList<>();
            repoCache.put(repo.getOwner().getLogin(), reposForOwner);
        }
        reposForOwner.add(repo);
    }
}
