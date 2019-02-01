package com.example.quizapp.api;

import com.example.quizapp.response.AdvertisementResponse;
import com.example.quizapp.response.HomeFeedResponse;
import com.example.quizapp.response.LikeDislikePost;
import com.example.quizapp.response.NotificationResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IConnectAPI {
    @GET("/post/userWall/{userId}")
    public Call<HomeFeedResponse> getHomeFeedResponse(@Path("userId") String userId);

    @POST("/post/like/{postId}/{userId}")
    public Call<LikeDislikePost> postLike(@Path("postId") String postId, @Path("userId") String userId);

    @DELETE("/post/dislike/{postId}/{userId}")
    public Call<LikeDislikePost> postDislike(@Path("postId") String postId, @Path("userId") String userId);

    @GET("/notification/{userId}")
    public Call<NotificationResponse> getNotifications(@Path("userId") String userId);

    @GET("/advertisement/bulk/{userId}{count}")
    public Call<AdvertisementResponse> getAdvertisements(@Path("userId") String userId, @Path("count") Integer count);

}
