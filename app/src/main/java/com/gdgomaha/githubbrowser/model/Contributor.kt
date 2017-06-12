package com.gdgomaha.githubbrowser.model

import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback
import com.google.gson.annotations.SerializedName

data class Contributor(
        val login: String,
        val id: Long,
        @SerializedName("avatar_url") val avatarUrl: String?
) : SimpleDiffCallback.RecyclerItem {

    override fun itemId(): Int {
        return id.toInt()
    }
}
