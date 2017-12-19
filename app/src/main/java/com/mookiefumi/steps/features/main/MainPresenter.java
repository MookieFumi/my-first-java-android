package com.mookiefumi.steps.features.main;

import com.mookiefumi.steps.services.base.ApiCommunication;
import com.mookiefumi.steps.services.pojos.Repo;
import com.mookiefumi.steps.services.GitHubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter implements IMainPresenter {

    private IMainView view;
    private  List<Repo> repos;

    public MainPresenter(IMainView view){
        this.view = view;
        this.repos = new ArrayList<Repo>();
    }

    @Override
    public List<Repo> getItems() {
        return repos;
    }

    @Override
    public void setItems(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public void getReposFromApi(String user){

        view.SetBusy(true);

        Retrofit retrofit = ApiCommunication.getInstance().getRetrofit();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> call = service.getRepos(user);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                switch (response.code()) {
                    case 200:
                        List<Repo> data = response.body();
                        repos = data;
                        view.NotifyDataSetChanged();
                        break;
                    case 401:
                        break;
                    default:
                        break;
                }
                view.SetBusy(false);
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                view.SetBusy(false);
                view.ShowMessage("Error ocurred during operation");
            }
        });
    }
}

