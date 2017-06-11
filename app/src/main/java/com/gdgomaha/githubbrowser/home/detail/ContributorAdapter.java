package com.gdgomaha.githubbrowser.home.detail;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.model.Contributor;
import com.gdgomaha.githubbrowser.utils.SimpleDiffCallback;

import java.util.ArrayList;
import java.util.List;

final class ContributorAdapter extends RecyclerView.Adapter<ContributorViewHolder> {

    private final List<Contributor> data = new ArrayList<>();

    void setData(List<Contributor> contributors) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SimpleDiffCallback(data, contributors));
        data.clear();
        data.addAll(contributors);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override public ContributorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContributorViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user_list_item, parent, false));
    }

    @Override public void onBindViewHolder(ContributorViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override public int getItemCount() {
        return data.size();
    }
}
