package com.gdgomaha.githubbrowser.home.list;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.model.Repo;
import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback;

import java.util.ArrayList;
import java.util.List;

public final class RepoListAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private final List<Repo> data = new ArrayList<>();
    private final RepoListPresenter presenter;

    RepoListAdapter(RepoListPresenter presenter) {
        this.presenter = presenter;
        setHasStableIds(true);
    }

    void setData(List<Repo> repos) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new SimpleDiffCallback(data, repos));
        this.data.clear();
        this.data.addAll(repos);
        result.dispatchUpdatesTo(this);
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_repo_list_item, viewGroup, false);
        return new RepoViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder repoViewHolder, int position) {
        repoViewHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }
}
