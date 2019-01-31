package com.example.quizapp.api;

import com.example.quizapp.response.HomeFeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IConnectAPI {
    @GET("/post/userWall/{userId}")
    public Call<HomeFeedResponse> getHomeFeedResponse(@Path("userId") String userId);
}
