package com.gdgomaha.githubbrowser.model;

import com.google.gson.annotations.SerializedName;

public final class Owner {

    private long id;
    private String login;
    @SerializedName("avatar_url") private String avatarUrl;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
