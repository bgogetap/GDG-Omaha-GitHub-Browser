package com.gdgomaha.githubbrowser.data;

import com.gdgomaha.githubbrowser.model.Repo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class RepoResponse {

    @SerializedName("items") public final List<Repo> repos;

    RepoResponse(List<Repo> repos) {
        this.repos = repos;
    }
}
