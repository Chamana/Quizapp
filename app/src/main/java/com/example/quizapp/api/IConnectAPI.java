package com.example.quizapp.api;

import com.example.quizapp.models.response.PostListItem;
import com.example.quizapp.models.response.UserFeedResponse;
import com.example.quizapp.models.response.UserProfileResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IConnectAPI {

    @GET("/post/findByUserId/{userId}")
    public Call<UserFeedResponse> getUserFeed(@Path("userId")int userId);
    @GET("follow/getFollowResponse/{userId}")
    public Call<UserProfileResponse> getUserInfo(@Path("userId")int userId);
}
