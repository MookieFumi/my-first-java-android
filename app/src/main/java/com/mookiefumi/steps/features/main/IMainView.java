package com.mookiefumi.steps.features.main;

public interface IMainView{
    void NotifyDataSetChanged();
    void ShowMessage(String message);
    void Search(String searchText);
    void SetBusy(Boolean busy);
}

