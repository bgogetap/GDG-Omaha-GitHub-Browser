package com.gdgomaha.githubbrowser.home;

import android.os.Bundle;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.home.list.RepoListFragment;

import dagger.android.support.DaggerAppCompatActivity;

public final class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, new RepoListFragment())
                    .commit();
        }
    }
}
