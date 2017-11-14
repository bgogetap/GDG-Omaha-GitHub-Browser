package com.gdgomaha.githubbrowser.home.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.model.Repo;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

final class RepoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_repo_name) TextView repoNameText;
    @BindView(R.id.tv_repo_description) TextView repoDescriptionText;
    @BindView(R.id.tv_star_count) TextView starCountText;
    @BindView(R.id.tv_fork_count) TextView forkCountText;

    private final RepoListPresenter presenter;

    private Repo repo;

    RepoViewHolder(View itemView, RepoListPresenter presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.presenter = presenter;
    }

    void bind(Repo repo) {
        this.repo = repo;
        repoNameText.setText(repo.getName());
        repoDescriptionText.setText(repo.getDescription());
        starCountText.setText(NumberFormat.getInstance().format(repo.getStargazersCount()));
        forkCountText.setText(NumberFormat.getInstance().format(repo.getForksCount()));
    }

    @OnClick(R.id.repo_list_item_parent)
    void clicked() {
        if (repo != null) {
            presenter.repoSelected(repo);
        }
    }
}
