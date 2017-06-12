package com.gdgomaha.githubbrowser.home.list

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gdgomaha.githubbrowser.R
import com.gdgomaha.githubbrowser.extensions.inflate
import com.gdgomaha.githubbrowser.model.Repo
import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback
import java.util.*

class RepoListAdapter constructor(private val presenter: RepoListPresenter) : RecyclerView.Adapter<RepoViewHolder>() {

    private val data = ArrayList<Repo>()

    init {
        setHasStableIds(true)
    }

    fun setData(repos: List<Repo>) {
        DiffUtil.calculateDiff(SimpleDiffCallback(data, repos)).let {
            data.clear()
            data.addAll(repos)
            it.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RepoViewHolder {
        val view = viewGroup inflate R.layout.view_repo_list_item
        return RepoViewHolder(view, presenter)
    }

    override fun onBindViewHolder(repoViewHolder: RepoViewHolder, position: Int) {
        repoViewHolder.bind(data[position])
    }

    override fun getItemCount() = data.size

    override fun getItemId(position: Int) = data[position].id
}
