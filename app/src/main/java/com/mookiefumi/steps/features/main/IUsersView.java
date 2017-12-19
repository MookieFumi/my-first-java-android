package com.mookiefumi.steps.features.main;

public interface IUsersView {
    void NotifyDataSetChanged();

    void ShowMessage(String message);

    void Search();

    void SetBusy(Boolean busy);
}

