package com.mookiefumi.steps.features.main;

import com.mookiefumi.steps.services.GitHubService;
import com.mookiefumi.steps.services.base.ApiBaseCommunication;
import com.mookiefumi.steps.services.pojos.Result;
import com.mookiefumi.steps.services.pojos.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersPresenter implements IUsersPresenter {

    private IUsersView view;
    private Result<User> result;

    public UsersPresenter(IUsersView view) {
        this.view = view;
        this.result = new Result<User>();
        this.result.setItems(new ArrayList<User>());
    }

    @Override
    public Result<User> getResult() {
        return result;
    }

    @Override
    public void setResult(Result<User> result) {
        this.result = result;
    }

    @Override
    public void getUsersFromApi() {

        view.SetBusy(true);

        Retrofit retrofit = ApiBaseCommunication.getInstance().getRetrofit();
        GitHubService service = retrofit.create(GitHubService.class);

        Call<Result<User>> call = service.getUsers();
        call.enqueue(new Callback<Result<User>>() {
            @Override
            public void onResponse(Call<Result<User>> call, Response<Result<User>> response) {
                switch (response.code()) {
                    case 200:
                        Result<User> data = response.body();
                        result = data;
                        view.NotifyDataSetChanged();
                        break;
                    default:
                        view.ShowMessage("Error ocurred during operation");
                }
                view.SetBusy(false);
            }

            @Override
            public void onFailure(Call<Result<User>> call, Throwable t) {
                view.SetBusy(false);
                view.ShowMessage("Error ocurred during operation");
            }
        });
    }
}

