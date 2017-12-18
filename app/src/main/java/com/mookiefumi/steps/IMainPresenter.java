package com.mookiefumi.steps;

import com.mookiefumi.steps.services.pojos.Repo;

import java.util.List;

public interface IMainPresenter
{
    void getRepos();
    List<Repo> getItems();
    void setItems(List<Repo> repos);
}

