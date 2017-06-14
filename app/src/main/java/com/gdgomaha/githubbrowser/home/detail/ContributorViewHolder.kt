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

class ContributorViewHolder(
        itemView: View,
        private val favoriteService: FavoriteService
) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tv_user_name) lateinit var userNameText: TextView
    @BindView(R.id.iv_avatar) lateinit var avatarImageView: ImageView

    private var contributor: Contributor? = null

    init {
        ButterKnife.bind(this, itemView)
        itemView.setOnLongClickListener {
            contributor?.let { favoriteService.toggleStar(it) }
            true
        }
    }

    fun bind(contributor: ContributorAdapter.ResolvedContributor) {
        this.contributor = contributor.contributor
        userNameText.text = contributor.contributor.login
        itemView.setBackgroundColor(itemView.getColor(
                if (contributor.favorited) R.color.starred_background else R.color.un_starred_background))
        Glide.with(avatarImageView)
                .load(contributor.contributor.avatarUrl)
                .into(avatarImageView)
    }
}
