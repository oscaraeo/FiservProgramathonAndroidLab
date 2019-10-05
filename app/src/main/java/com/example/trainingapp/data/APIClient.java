package com.example.trainingapp.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIClient {
    @GET("/users.json")
    Call<List<User>> getAllUsers();

    @GET("/users/{id}.json")
    Call<User> getUserWithID(@Path("id") String userID);

    @PUT("/users/{id}.json")
    Call<User> updateUserWithID(@Path("id") String userID, @Body User user);

    @POST("/users.json")
    Call<User> createUser(@Body User user);

    @DELETE("/users/{id}.json")
    Call<User> deleteUserWithID(@Path("id") String userID);
}
