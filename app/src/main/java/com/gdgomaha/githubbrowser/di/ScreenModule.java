package com.gdgomaha.githubbrowser.di;

import com.gdgomaha.githubbrowser.utils.DisposableManager;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.PublishSubject;

@Module
public abstract class ScreenModule {

    @Provides
    @ScreenScope
    @Named("scope_subject")
    static PublishSubject<Boolean> provideScopeSubject() {
        return PublishSubject.create();
    }

    @Provides
    @ScreenScope
    @ForScreen
    static DisposableManager provideDisposableManager(
            @Named("scope_subject") PublishSubject<Boolean> scopeSubject) {
        return new DisposableManager(scopeSubject);
    }
}
