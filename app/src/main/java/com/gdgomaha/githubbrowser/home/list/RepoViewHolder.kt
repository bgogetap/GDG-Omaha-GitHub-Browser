package com.gdgomaha.githubbrowser.home.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.gdgomaha.githubbrowser.R
import com.gdgomaha.githubbrowser.model.Repo
import java.text.NumberFormat

class RepoViewHolder (itemView: View, private val repoSelected: (Repo) -> Unit) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tv_repo_name) lateinit var repoNameText: TextView
    @BindView(R.id.tv_repo_description) lateinit var repoDescriptionText: TextView
    @BindView(R.id.tv_star_count) lateinit var starCountText: TextView
    @BindView(R.id.tv_fork_count) lateinit var forkCountText: TextView

    private var repo: Repo? = null

    init {
        ButterKnife.bind(this, itemView)
    }

    internal fun bind(repo: Repo) {
        this.repo = repo
        repoNameText.text = repo.name
        repoDescriptionText.text = repo.description
        starCountText.text = NumberFormat.getInstance().format(repo.stargazersCount)
        forkCountText.text = NumberFormat.getInstance().format(repo.forksCount)
    }

    @OnClick(R.id.repo_list_item_parent) internal fun clicked() {
        repo?.let { repoSelected.invoke(it) }
    }
}
