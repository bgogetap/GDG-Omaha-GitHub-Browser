package com.gdgomaha.githubbrowser.model

import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback
import com.google.gson.annotations.SerializedName

import org.threeten.bp.ZonedDateTime

data class Repo(
        val id: Long,
        val name: String,
        val description: String,
        @SerializedName("stargazers_count") val stargazersCount: Long,
        @SerializedName("forks_count") val forksCount: Long,
        val owner: Owner,
        @SerializedName("contributors_url") val contributorsUrl: String,
        @SerializedName("created_at") val createdDate: ZonedDateTime,
        @SerializedName("updated_at") val updatedDate: ZonedDateTime
) : SimpleDiffCallback.RecyclerItem {

    override fun itemId() = id

}
