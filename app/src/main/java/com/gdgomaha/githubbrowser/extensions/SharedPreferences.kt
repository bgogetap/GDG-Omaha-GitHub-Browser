package com.gdgomaha.githubbrowser.extensions

import android.content.SharedPreferences
import com.gdgomaha.githubbrowser.model.Contributor

const val STARRED_CONTRIBUTORS = "starred_contributors"

private val cache = HashSet<String>()
private var initialized = false

fun SharedPreferences.starContributor(contributor: Contributor): Set<String> {
    if (!initialized) updateCache()

    return if (!cache.contains(contributor.login)) {
        edit().putString(STARRED_CONTRIBUTORS, cache.apply { add(contributor.login) }.joinToString(separator = ","))
                .apply()
        updateCache()
    } else {
        cache
    }
}

fun SharedPreferences.unStarContributor(contributor: Contributor): Set<String> {
    if (!initialized) updateCache()

    return if (cache.contains(contributor.login)) {
        edit().putString(STARRED_CONTRIBUTORS, cache.apply { remove(contributor.login) }.joinToString(separator = ","))
                .apply()
        updateCache()
    } else {
        cache
    }
}


fun SharedPreferences.isContributorStarred(contributor: Contributor): Boolean {
    if (!initialized) updateCache()

    return cache.contains(contributor.login)
}

private fun SharedPreferences.getStarredContributors(): Set<String> {
    return getString(STARRED_CONTRIBUTORS, null)?.let {
        cache.run {
            clear()
            addAll(it.split(","))
        }
        cache
    } ?: emptySet<String>()
}

private fun SharedPreferences.updateCache(): Set<String> {
    cache.clear()
    cache.addAll(getStarredContributors())
    return cache
}