package com.gdgomaha.githubbrowser.home;

import android.support.v4.app.Fragment;

import com.gdgomaha.githubbrowser.di.ScreenModule;
import com.gdgomaha.githubbrowser.di.ScreenScope;
import com.gdgomaha.githubbrowser.home.detail.RepoDetailComponent;
import com.gdgomaha.githubbrowser.home.detail.RepoDetailFragment;
import com.gdgomaha.githubbrowser.home.list.RepoListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = RepoDetailComponent.class)
public abstract class MainScreenBindingModule {

    @ScreenScope
    @ContributesAndroidInjector(modules = ScreenModule.class)
    abstract RepoListFragment repoListFragment();

    @Binds
    @IntoMap
    @FragmentKey(RepoDetailFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindDetailInjector(RepoDetailComponent.Builder injectorBuilder);
}
