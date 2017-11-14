package com.gdgomaha.githubbrowser.home.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public final class RepoListFragment extends BaseFragment {

    @Inject RepoListViewModel viewModel;
    @Inject RepoListPresenter presenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.loading_indicator) View loadingIndicator;
    @BindView(R.id.tv_error) TextView errorText;

    @Override
    protected void onViewBound() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RepoListAdapter(presenter));
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_list;
    }

    @Override
    protected Disposable[] subscribeToViewModel() {
        return new Disposable[]{
                viewModel.repos().subscribe(((RepoListAdapter) recyclerView.getAdapter())::setData),
                viewModel.loadingVisibility().subscribe(loadingIndicator::setVisibility),
                viewModel.listVisibility().subscribe(recyclerView::setVisibility),
                viewModel.error().subscribe(errorRes -> {
                    if (errorRes == -1) {
                        errorText.setVisibility(View.GONE);
                        errorText.setText(null);
                    } else {
                        errorText.setVisibility(View.VISIBLE);
                        errorText.setText(errorRes);
                    }
                })
        };
    }
}
