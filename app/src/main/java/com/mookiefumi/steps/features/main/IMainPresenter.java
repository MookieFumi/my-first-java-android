package com.mookiefumi.steps.features.main;

import com.mookiefumi.steps.services.pojos.Repo;

import java.util.List;

public interface IMainPresenter
{
    void getReposFromApi(String user);

    List<Repo> getItems();
    void setItems(List<Repo> repos);
}

