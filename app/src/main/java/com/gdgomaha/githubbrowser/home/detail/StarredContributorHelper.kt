package com.gdgomaha.githubbrowser.home.detail

import android.content.SharedPreferences
import com.gdgomaha.githubbrowser.di.ScreenScope
import com.gdgomaha.githubbrowser.extensions.isContributorStarred
import com.gdgomaha.githubbrowser.extensions.starContributor
import com.gdgomaha.githubbrowser.extensions.unStarContributor
import com.gdgomaha.githubbrowser.model.Contributor
import com.jakewharton.rxrelay2.PublishRelay
import javax.inject.Inject

@ScreenScope
class StarredContributorHelper @Inject constructor(private val sharedPreferences: SharedPreferences, private val starChangedEvents: PublishRelay<Set<String>>) {

    fun isContributorStarred(contributor: Contributor): Boolean {
        return sharedPreferences.isContributorStarred(contributor)
    }

    fun toggleStar(contributor: Contributor) {
        starChangedEvents.accept(
                if (isContributorStarred(contributor)) {
                    sharedPreferences.unStarContributor(contributor)
                } else {
                    sharedPreferences.starContributor(contributor)
                })
    }
}