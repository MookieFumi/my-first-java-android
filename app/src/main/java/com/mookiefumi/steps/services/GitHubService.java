package com.mookiefumi.steps.services;

import com.mookiefumi.steps.services.pojos.Repo;
import com.mookiefumi.steps.services.pojos.Result;
import com.mookiefumi.steps.services.pojos.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> getRepos(@Path("user") String user);
    @GET("/search/users?q=repos:>1+followers:>5")
    Call<Result<User>> getUsers(@Query("page") int page, @Query("pageSize") int pageSize );
}

