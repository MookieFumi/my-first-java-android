package com.mookiefumi.steps.features.repos;

import com.mookiefumi.steps.services.pojos.Repo;

import java.util.List;

public interface IReposPresenter {
    void getReposFromApi(String user);

    List<Repo> getItems();

    void setItems(List<Repo> repos);
}

