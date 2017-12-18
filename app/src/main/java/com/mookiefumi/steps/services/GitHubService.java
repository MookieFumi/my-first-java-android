package com.mookiefumi.steps.services;

import com.mookiefumi.steps.services.pojos.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> getRepos(@Path("user") String user);
}
