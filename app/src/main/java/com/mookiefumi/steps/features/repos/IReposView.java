package com.mookiefumi.steps.features.repos;

public interface IReposView {
    void NotifyDataSetChanged();

    void ShowMessage(String message);

    void Search(String searchText);

    void SetBusy(Boolean busy);
}

