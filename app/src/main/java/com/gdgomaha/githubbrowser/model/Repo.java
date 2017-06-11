package com.gdgomaha.githubbrowser.model;

import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback;
import com.google.gson.annotations.SerializedName;

import org.threeten.bp.ZonedDateTime;

public final class Repo implements SimpleDiffCallback.RecyclerItem {

    private long id;
    private String name;
    @SerializedName("full_name") private String fullName;
    private String description;
    @SerializedName("stargazers_count") private long stargazersCount;
    private String language;
    @SerializedName("forks_count") private long forksCount;
    private Owner owner;
    @SerializedName("contributors_url") private String contributorsUrl;
    @SerializedName("created_at") private ZonedDateTime createdDate;
    @SerializedName("updated_at") private ZonedDateTime updatedDate;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public long getStargazersCount() {
        return stargazersCount;
    }

    public String getLanguage() {
        return language;
    }

    public long getForksCount() {
        return forksCount;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getContributorsUrl() {
        return contributorsUrl;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override public int itemId() {
        return (int) getId();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repo repo = (Repo) o;

        if (id != repo.id) return false;
        if (stargazersCount != repo.stargazersCount) return false;
        if (forksCount != repo.forksCount) return false;
        if (!name.equals(repo.name)) return false;
        if (!fullName.equals(repo.fullName)) return false;
        if (!description.equals(repo.description)) return false;
        if (!language.equals(repo.language)) return false;
        if (!owner.equals(repo.owner)) return false;
        if (!contributorsUrl.equals(repo.contributorsUrl)) return false;
        if (!createdDate.equals(repo.createdDate)) return false;
        return updatedDate.equals(repo.updatedDate);
    }

    @Override public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (int) (stargazersCount ^ (stargazersCount >>> 32));
        result = 31 * result + language.hashCode();
        result = 31 * result + (int) (forksCount ^ (forksCount >>> 32));
        result = 31 * result + owner.hashCode();
        result = 31 * result + contributorsUrl.hashCode();
        result = 31 * result + createdDate.hashCode();
        result = 31 * result + updatedDate.hashCode();
        return result;
    }
}
