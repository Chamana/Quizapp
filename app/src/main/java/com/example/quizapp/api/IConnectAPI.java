package com.example.quizapp.api;

import com.example.quizapp.adapter.LikeDislikePost;
import com.example.quizapp.models.response.Follow;
import com.example.quizapp.models.response.FollowResponse;
import com.example.quizapp.models.response.PostListItem;
import com.example.quizapp.models.response.UserFeedResponse;
import com.example.quizapp.models.response.UserProfileResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IConnectAPI {

    @GET("/post/findByUserId/{userId}")
    public Call<UserFeedResponse> getUserFeed(@Path("userId")String userId);
    @GET("follow/getFollowResponse/{userId}")
    public Call<UserProfileResponse> getUserInfo(@Path("userId")String userId);
    @POST("/post/like/{postId}/{userId}")
    public Call<LikeDislikePost> postLike(@Path("postId") String postId, @Path("userId") String userId);
    @POST("/carts/add")
    public Call<FollowResponse> follow(@Body Follow follow);

    @DELETE("/post/dislike/{postId}/{userId}")
    public Call<LikeDislikePost> postDislike(@Path("postId") String postId, @Path("userId") String userId);
}
