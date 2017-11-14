package com.gdgomaha.githubbrowser.home.list;

import android.view.View;

import com.gdgomaha.githubbrowser.R;
import com.gdgomaha.githubbrowser.data.RepoResponse;
import com.gdgomaha.githubbrowser.model.Repo;
import com.gdgomaha.githubbrowser.test.JsonLoader;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static com.gdgomaha.githubbrowser.test.GsonHelper.GSON;

public class RepoListViewModelTest {

    private RepoListViewModel viewModel;
    private TestObserver<List<Repo>> repoObserver = new TestObserver<>();
    private TestObserver<Integer> listVisibilityObserver = new TestObserver<>();
    private TestObserver<Integer> loadingVisibilityObserver = new TestObserver<>();

    @Before
    public void setUp() {
        viewModel = new RepoListViewModel();
        viewModel.repos().subscribe(repoObserver);
        viewModel.listVisibility().subscribe(listVisibilityObserver);
        viewModel.loadingVisibility().subscribe(loadingVisibilityObserver);
    }

    @Test
    public void reposLoaded() {
        RepoResponse response = GSON.fromJson(JsonLoader.forFile("model/get_trending_repos.json"), RepoResponse.class);

        viewModel.toggleLoading();
        viewModel.processRepos(response.repos, null);

        repoObserver.assertValue(response.repos);
        viewModel.error().test().assertValue(-1);
        listVisibilityObserver.assertValues(View.GONE, View.VISIBLE);
        loadingVisibilityObserver.assertValues(View.VISIBLE, View.GONE);
    }

    @Test
    public void error() {
        viewModel.toggleLoading();
        viewModel.processRepos(null, new IOException());

        repoObserver.assertNoValues();
        listVisibilityObserver.assertValues(View.GONE, View.GONE);
        viewModel.error().test().assertValue(R.string.api_error_repos);
        loadingVisibilityObserver.assertValues(View.VISIBLE, View.GONE);
    }
}