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

internal class ContributorAdapter @Inject constructor(
        starsUpdatedEvents: PublishRelay<Set<String>>,
        private val starredContributorHelper: StarredContributorHelper
) : RecyclerView.Adapter<ContributorViewHolder>() {

    private val data = ArrayList<Contributor>()

    init {
        setHasStableIds(true)
        starsUpdatedEvents.subscribe { notifyDataSetChanged() }
    }

    fun setData(contributors: List<Contributor>) {
        val diffResult = DiffUtil.calculateDiff(SimpleDiffCallback(data, contributors))
        data.clear()
        data.addAll(contributors)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        return ContributorViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_user_list_item, parent, false), starredContributorHelper)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int) = data[position].id
}
