package com.gdgomaha.githubbrowser.home.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.model.Contributor;

import butterknife.BindView;
import butterknife.ButterKnife;

final class ContributorViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_user_name) TextView userNameText;
    @BindView(R.id.iv_avatar) ImageView avatarImageView;

    ContributorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bind(Contributor contributor) {
        userNameText.setText(contributor.getLogin());
        Glide.with(avatarImageView)
                .load(contributor.getAvatarUrl())
                .into(avatarImageView);
    }
}
