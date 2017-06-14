package com.gdgomaha.githubbrowser.extensions

import android.content.SharedPreferences
import com.gdgomaha.githubbrowser.model.Contributor

const val FAVORITE_KEY = "favorited_contributors"

private val cache = HashSet<String>()
private var initialized = false

fun SharedPreferences.isContributorFavorited(contributor: Contributor): Boolean {
    if (!initialized) loadFavoritedContributors()

    return cache.contains(contributor.login)
}

fun SharedPreferences.toggleFavorite(contributor: Contributor) {
    when (isContributorFavorited(contributor)) {
        true -> cache.remove(contributor.login)
        false -> cache.add(contributor.login)
    }
    persist(cache)
}

private fun SharedPreferences.loadFavoritedContributors() {
    cache.clear()
    cache.addAll(getString(FAVORITE_KEY, null)?.split(",") ?: emptyList())
}

private fun SharedPreferences.persist(cache: Set<String>) {
    edit().putString(FAVORITE_KEY, cache.joinToString(separator = ",")).apply()
}