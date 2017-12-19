package com.mookiefumi.steps.services.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBaseCommunication {

    private static ApiBaseCommunication instance = null;
    private Retrofit retrofit;

    private ApiBaseCommunication() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiBaseCommunication getInstance() {
        if (instance == null) {
            instance = new ApiBaseCommunication();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}
