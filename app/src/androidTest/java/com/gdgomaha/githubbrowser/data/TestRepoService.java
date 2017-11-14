package com.gdgomaha.githubbrowser.data;

import com.gdgomaha.githubbrowser.model.Contributor;
import com.gdgomaha.githubbrowser.model.Repo;
import com.gdgomaha.githubbrowser.test.TestUtils;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class TestRepoService implements RepoService {

    private final TestUtils testUtils;

    private boolean sendError;

    @Inject
    TestRepoService(TestUtils testUtils) {
        this.testUtils = testUtils;
    }

    @Override
    public Single<RepoResponse> getRecentTrendingRepos() {
        if (!sendError) {
            return Single.just(testUtils.loadJson("mock/get_trending_repos.json", RepoResponse.class));
        }
        return Single.error(new IOException());
    }

    @Override
    public Single<Repo> getRepo(String owner, String repoName) {
        throw new UnsupportedOperationException("TODO");//TODO
    }

    @Override
    public Single<List<Contributor>> getContributors(String url) {
        throw new UnsupportedOperationException("TODO");//TODO
    }

    public void setSendError(boolean sendError) {
        this.sendError = sendError;
    }
}
