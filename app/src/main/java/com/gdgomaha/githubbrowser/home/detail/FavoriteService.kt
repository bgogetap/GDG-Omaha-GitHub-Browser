package com.gdgomaha.githubbrowser.home.detail

import android.content.SharedPreferences
import com.gdgomaha.githubbrowser.di.ScreenScope
import com.gdgomaha.githubbrowser.extensions.isContributorFavorited
import com.gdgomaha.githubbrowser.extensions.toggleFavorite
import com.gdgomaha.githubbrowser.model.Contributor
import com.jakewharton.rxrelay2.PublishRelay
import javax.inject.Inject

@ScreenScope
class FavoriteService @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val starChangedEvents: PublishRelay<Boolean>) {

    fun isContributorStarred(contributor: Contributor): Boolean {
        return sharedPreferences.isContributorFavorited(contributor)
    }

    fun toggleStar(contributor: Contributor) {
        sharedPreferences.toggleFavorite(contributor)
        starChangedEvents.accept(true)
    }
}