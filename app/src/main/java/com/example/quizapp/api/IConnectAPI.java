package com.example.quizapp.api;

import com.example.quizapp.models.request.SharePostRequest;
import com.example.quizapp.models.request.SubscribeButtonRequest;
import com.example.quizapp.models.request.UserGetAllContestRequest;
import com.example.quizapp.models.response.GetAllContestResponse;
import com.example.quizapp.models.response.OverallLeaderboardResponse;
import com.example.quizapp.models.response.SharePostResponse;
import com.example.quizapp.models.response.contestDetails.GetContestDetailsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IConnectAPI {

    @POST("/contests/{contestId}")
    public Call<GetContestDetailsResponse> getContestDetails(@Path("contestId") String contestId, @Body UserGetAllContestRequest userGetAllContestRequest);

    @POST("/contests/{contestId}/subscribe")
    public Call<SubscribeButtonRequest> getIfSubscribed(@Path("contestId") String contestId, @Body UserGetAllContestRequest userGetAllContestRequest);

    @POST("/post/share")
    public Call<SharePostResponse> getPostResponse(@Body SharePostRequest sharePostRequest);

    @POST("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest(@Body UserGetAllContestRequest userGetAllContestRequest);

    @GET("/getReport/leaderboard")
    public Call<List<OverallLeaderboardResponse>> getLeaderboard();
}
