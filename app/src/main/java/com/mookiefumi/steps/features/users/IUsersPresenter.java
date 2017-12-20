package com.mookiefumi.steps.features.users;

import com.mookiefumi.steps.services.pojos.Result;
import com.mookiefumi.steps.services.pojos.User;

public interface IUsersPresenter {
    void getUsersFromApi(int page);

    Result<User> getResult();

    void setResult(Result<User>  result);

}

