package com.mookiefumi.steps.features.main;

import com.mookiefumi.steps.services.pojos.Result;
import com.mookiefumi.steps.services.pojos.User;

public interface IUsersPresenter {
    void getUsersFromApi();

    Result<User> getResult();

    void setResult(Result<User>  result);
}

