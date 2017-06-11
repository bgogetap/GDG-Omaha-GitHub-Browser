package com.gdgomaha.githubbrowser.utils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public final class DisposableManager {

    private final CompositeDisposable disposables = new CompositeDisposable();

    public DisposableManager(PublishSubject<Boolean> scopeExitSubject) {
        disposables.add(scopeExitSubject.subscribe(__ -> disposables.clear()));
    }

    public void add(Disposable... disposable) {
        disposables.addAll(disposable);
    }
}
