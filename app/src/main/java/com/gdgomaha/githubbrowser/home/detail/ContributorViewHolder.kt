package com.gdgomaha.githubbrowser.home.detail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.gdgomaha.githubbrowser.R
import com.gdgomaha.githubbrowser.extensions.getColor
import com.gdgomaha.githubbrowser.model.Contributor

internal class ContributorViewHolder(
        itemView: View,
        private val starredContributorHelper: StarredContributorHelper
) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tv_user_name) lateinit var userNameText: TextView
    @BindView(R.id.iv_avatar) lateinit var avatarImageView: ImageView

    private var contributor: Contributor? = null

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnLongClickListener {
            contributor?.let { starredContributorHelper.toggleStar(it) }
            true
        }
    }

    fun bind(contributor: Contributor) {
        this.contributor = contributor
        userNameText.text = contributor.login
        itemView.setBackgroundColor(itemView.getColor(
                if (starredContributorHelper.isContributorStarred(contributor)) R.color.starred_background else R.color.un_starred_background))
        Glide.with(avatarImageView)
                .load(contributor.avatarUrl)
                .into(avatarImageView)
    }
}
