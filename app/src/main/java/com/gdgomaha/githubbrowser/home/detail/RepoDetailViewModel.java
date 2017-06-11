package com.gdgomaha.githubbrowser.home.detail;

import android.view.View;

import com.gdgomaha.githubbrowser.di.ScreenScope;
import com.gdgomaha.githubbrowser.model.Contributor;
import com.gdgomaha.githubbrowser.model.Repo;
import com.jakewharton.rxrelay2.BehaviorRelay;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

@ScreenScope
public final class RepoDetailViewModel {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final BehaviorRelay<String> repoNameRelay = BehaviorRelay.create();
    private final BehaviorRelay<String> repoDescriptionRelay = BehaviorRelay.create();
    private final BehaviorRelay<String> creationDateRelay = BehaviorRelay.create();
    private final BehaviorRelay<String> updatedDateRelay = BehaviorRelay.create();
    private final BehaviorRelay<List<Contributor>> contributorsRelay = BehaviorRelay.create();
    private final BehaviorRelay<Integer> repoContentVisibilityRelay = BehaviorRelay.createDefault(View.GONE);
    private final BehaviorRelay<Integer> repoLoadingVisibilityRelay = BehaviorRelay.createDefault(View.VISIBLE);
    private final BehaviorRelay<Integer> contributorContentVisibilityRelay = BehaviorRelay.createDefault(View.GONE);
    private final BehaviorRelay<Integer> contributorLoadingVisibilityRelay = BehaviorRelay.createDefault(View.VISIBLE);

    @Inject RepoDetailViewModel() {

    }

    Observable<String> repoName() {
        return repoNameRelay;
    }

    Observable<String> repoDescription() {
        return repoDescriptionRelay;
    }

    Observable<String> creationDate() {
        return creationDateRelay;
    }

    Observable<String> updatedDate() {
        return updatedDateRelay;
    }

    Observable<List<Contributor>> contributors() {
        return contributorsRelay;
    }

    Observable<Integer> repoContentVisibility() {
        return repoContentVisibilityRelay;
    }

    Observable<Integer> repoLoadingVisibility() {
        return repoLoadingVisibilityRelay;
    }

    Observable<Integer> contributorContentVisibility() {
        return contributorContentVisibilityRelay;
    }

    Observable<Integer> contributorLoadingVisibility() {
        return contributorLoadingVisibilityRelay;
    }

    void processRepo(Repo repo) {
        repoNameRelay.accept(repo.getName());
        repoDescriptionRelay.accept(repo.getDescription());
        creationDateRelay.accept(repo.getCreatedDate().format(FORMATTER));
        updatedDateRelay.accept(repo.getUpdatedDate().format(FORMATTER));
        repoContentVisibilityRelay.accept(View.VISIBLE);
        repoLoadingVisibilityRelay.accept(View.GONE);
    }

    void processContributors(List<Contributor> contributors, Throwable error) {
        if (error != null) {
            Timber.e(error, "Error loading Repo or Contributors");
        } else {
            contributorsRelay.accept(contributors);
        }
        contributorLoadingVisibilityRelay.accept(View.GONE);
        contributorContentVisibilityRelay.accept(View.VISIBLE);
    }
}
