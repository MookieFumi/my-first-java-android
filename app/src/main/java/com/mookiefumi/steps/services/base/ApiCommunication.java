package com.mookiefumi.steps.services.base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCommunication {

    private static ApiCommunication instance = null;
    private Retrofit retrofit;

    private ApiCommunication() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiCommunication getInstance() {
        if (instance == null) {
            instance = new ApiCommunication();
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
