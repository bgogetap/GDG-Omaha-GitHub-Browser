package com.gdgomaha.githubbrowser.model;

import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback;
import com.google.gson.annotations.SerializedName;

public final class Contributor implements SimpleDiffCallback.RecyclerItem {

    private String login;
    private long id;
    @SerializedName("avatar_url") private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public long getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public long itemId() {
        return getId();
    }
}
