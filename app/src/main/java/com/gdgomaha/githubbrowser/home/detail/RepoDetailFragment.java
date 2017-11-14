package com.gdgomaha.githubbrowser.home.detail;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.base.BaseFragment;
import com.gdgomaha.githubbrowser.model.Repo;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public final class RepoDetailFragment extends BaseFragment {

    static final String OWNER_KEY = "owner";
    static final String REPO_KEY = "repo";

    public static RepoDetailFragment newInstance(Repo repo) {
        Bundle bundle = new Bundle();
        bundle.putString(OWNER_KEY, repo.getOwner().getLogin());
        bundle.putString(REPO_KEY, repo.getName());
        RepoDetailFragment fragment = new RepoDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject RepoDetailViewModel viewModel;
    @Inject RepoDetailPresenter presenter;

    @BindView(R.id.tv_repo_name) TextView repoNameText;
    @BindView(R.id.tv_repo_description) TextView repoDescriptionText;
    @BindView(R.id.tv_creation_date) TextView createdDateText;
    @BindView(R.id.tv_updated_date) TextView updatedDateText;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.content) View contentView;
    @BindView(R.id.loading_indicator) View loadingIndicator;
    @BindView(R.id.contributor_loading_indicator) View contributorLoadingIndicator;

    @Override
    protected void onViewBound() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ContributorAdapter());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    protected Disposable[] subscribeToViewModel() {
        return new Disposable[]{
                viewModel.repoName().subscribe(repoNameText::setText),
                viewModel.repoDescription().subscribe(repoDescriptionText::setText),
                viewModel.creationDate().subscribe(createdDateText::setText),
                viewModel.updatedDate().subscribe(updatedDateText::setText),
                viewModel.contributors().subscribe(((ContributorAdapter) recyclerView.getAdapter())::setData),
                viewModel.repoContentVisibility().subscribe(contentView::setVisibility),
                viewModel.repoLoadingVisibility().subscribe(loadingIndicator::setVisibility),
                viewModel.contributorContentVisibility().subscribe(recyclerView::setVisibility),
                viewModel.contributorLoadingVisibility().subscribe(contributorLoadingIndicator::setVisibility)
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_detail;
    }
}
