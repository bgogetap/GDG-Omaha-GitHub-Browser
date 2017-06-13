package com.gdgomaha.githubbrowser.utils;

import android.support.v7.util.DiffUtil;

import java.util.List;

public final class SimpleDiffCallback extends DiffUtil.Callback {

    private final List<? extends RecyclerItem> oldList;
    private final List<? extends RecyclerItem> newList;

    public SimpleDiffCallback(List<? extends RecyclerItem> oldList, List<? extends RecyclerItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override public int getOldListSize() {
        return oldList.size();
    }

    @Override public int getNewListSize() {
        return newList.size();
    }

    @Override public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).itemId() == newList.get(newPosition).itemId();
    }

    @Override public boolean areContentsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).equals(newList.get(newPosition));
    }

    public interface RecyclerItem {

        long itemId();
    }
}
