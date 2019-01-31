package com.example.quizapp.api;

import com.example.quizapp.models.request.UserGetAllContestRequest;
import com.example.quizapp.models.response.contestDetails.GetContestDetailsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.quizapp.models.response.GetAllContestResponse;
import retrofit2.http.POST;

public interface IConnectAPI {

    @POST("/contests/{contestId}")
    public Call<GetContestDetailsResponse> getContestDetails(@Path("contestId") String contestId, @Body UserGetAllContestRequest userGetAllContestRequest);


    @POST("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest(@Body UserGetAllContestRequest userGetAllContestRequest);
}
