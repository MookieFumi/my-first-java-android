package com.mookiefumi.steps.services;

import com.mookiefumi.steps.services.base.ApiBaseCommunication;
import com.mookiefumi.steps.services.pojos.Repo;
import com.mookiefumi.steps.services.pojos.Result;
import com.mookiefumi.steps.services.pojos.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> getRepos(@Path("user") String user);
    @GET("search/users?q=tom+repos:>10+followers:>250")
    Call<Result<User>> getUsers();
}

