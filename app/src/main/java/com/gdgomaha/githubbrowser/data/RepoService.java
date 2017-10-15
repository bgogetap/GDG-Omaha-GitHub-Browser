package com.gdgomaha.githubbrowser.data;

import com.gdgomaha.githubbrowser.model.Repo;
import com.gdgomaha.githubbrowser.model.Contributor;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<RepoResponse> getRecentTrendingRepos();

    @GET("repos/{owner}/{repoName}")
    Single<Repo> getRepo(@Path("owner") String owner, @Path("repoName") String repoName);

    @GET
    Single<List<Contributor>> getContributors(@Url String url);
}
