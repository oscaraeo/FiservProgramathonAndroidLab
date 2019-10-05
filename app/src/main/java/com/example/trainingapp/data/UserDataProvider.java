package com.example.trainingapp.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDataProvider {
    static public APIClient getApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://generic-training.firebaseio.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIClient.class);
    }
}
