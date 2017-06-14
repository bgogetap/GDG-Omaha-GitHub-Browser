package com.gdgomaha.githubbrowser.home.detail

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gdgomaha.githubbrowser.R
import com.gdgomaha.githubbrowser.model.Contributor
import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback
import com.jakewharton.rxrelay2.PublishRelay
import java.util.*
import javax.inject.Inject

class ContributorAdapter @Inject constructor(
        starsUpdatedEvents: PublishRelay<Boolean>,
        private val favoriteService: FavoriteService
) : RecyclerView.Adapter<ContributorViewHolder>() {

    private val data = ArrayList<ResolvedContributor>()

    init {
        setHasStableIds(true)
        starsUpdatedEvents.subscribe { setData(data.map { it.contributor }) }
    }

    fun setData(contributors: List<Contributor>) {
        val newData = contributors.map { ResolvedContributor(it, favoriteService.isContributorStarred(it)) }
        val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(data, newData))
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        return ContributorViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_user_list_item, parent, false), favoriteService)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int) = data[position].contributor.id

    data class ResolvedContributor(val contributor: Contributor, val favorited: Boolean) : SimpleDiffCallback.RecyclerItem {

        override fun itemId(): Long {
            return contributor.id
        }
    }
}
