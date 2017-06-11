package com.gdgomaha.githubbrowser.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdgomaha.githubbrowser.di.ForScreen;
import com.gdgomaha.githubbrowser.utils.DisposableManager;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public abstract class BaseFragment extends DaggerFragment {

    @Inject @Named("scope_subject") PublishSubject<Boolean> scopeSubject;
    @Inject @ForScreen DisposableManager disposableManager;

    private Unbinder unbinder;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound();
        disposableManager.add(subscribeToViewModel());
        return view;
    }

    protected abstract void onViewBound();

    protected abstract int layoutRes();

    protected Disposable[] subscribeToViewModel() {
        return new Disposable[0];
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        scopeSubject.onNext(true);
    }
}
