package com.gdgomaha.githubbrowser.home.detail;

import android.view.View;

import com.gdgomaha.githubbrowser.model.Contributor;
import com.gdgomaha.githubbrowser.model.Repo;
import com.gdgomaha.githubbrowser.test.JsonLoader;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static com.gdgomaha.githubbrowser.test.GsonHelper.GSON;

public class RepoDetailViewModelTest {

    private TestObserver<String> nameObserver = new TestObserver<>();
    private TestObserver<String> descriptionObserver = new TestObserver<>();
    private TestObserver<String> creationDateObserver = new TestObserver<>();
    private TestObserver<String> updatedDateObserver = new TestObserver<>();
    private TestObserver<List<Contributor>> contributorObserver = new TestObserver<>();
    private TestObserver<Integer> repoContentVisibilityObserver = new TestObserver<>();
    private TestObserver<Integer> repoLoadingVisibilityObserver = new TestObserver<>();
    private TestObserver<Integer> contributorContentVisibilityObserver = new TestObserver<>();
    private TestObserver<Integer> contributorLoadingVisibilityObserver = new TestObserver<>();
    private RepoDetailViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new RepoDetailViewModel();
        viewModel.repoName().subscribe(nameObserver);
        viewModel.repoDescription().subscribe(descriptionObserver);
        viewModel.creationDate().subscribe(creationDateObserver);
        viewModel.updatedDate().subscribe(updatedDateObserver);
        viewModel.contributors().subscribe(contributorObserver);
        viewModel.repoContentVisibility().subscribe(repoContentVisibilityObserver);
        viewModel.repoLoadingVisibility().subscribe(repoLoadingVisibilityObserver);
        viewModel.contributorContentVisibility().subscribe(contributorContentVisibilityObserver);
        viewModel.contributorLoadingVisibility().subscribe(contributorLoadingVisibilityObserver);
    }

    @Test
    public void repo() {
        Repo repo = GSON.fromJson(JsonLoader.forFile("model/get_repo.json"), Repo.class);
        viewModel.processRepo(repo);
        nameObserver.assertValue("RxJava");
        descriptionObserver.assertValue("RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.");
        creationDateObserver.assertValue("2013-01-08 20:11");
        updatedDateObserver.assertValue("2017-06-10 21:57");
        repoContentVisibilityObserver.assertValues(View.GONE, View.VISIBLE);
        repoLoadingVisibilityObserver.assertValues(View.VISIBLE, View.GONE);
    }

    @Test
    public void contributors() {
        Type listType = new TypeToken<List<Contributor>>(){}.getType();
        List<Contributor> contributors = GSON.fromJson(JsonLoader.forFile("model/get_contributors.json"), listType);
        viewModel.processContributors(contributors, null);

        contributorContentVisibilityObserver.assertValues(View.GONE, View.VISIBLE);
        contributorLoadingVisibilityObserver.assertValues(View.VISIBLE, View.GONE);
        contributorObserver.assertValue(contributors);
    }

    @Test
    public void error() {
        viewModel.processContributors(null, new IOException());

        contributorContentVisibilityObserver.assertValues(View.GONE, View.VISIBLE);
        contributorLoadingVisibilityObserver.assertValues(View.VISIBLE, View.GONE);
    }
}