package com.example.quizapp.api;

import com.example.quizapp.models.response.contestDetails.GetContestDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.example.quizapp.models.response.GetAllContestResponse;

public interface IConnectAPI {

    @GET("/contests/{contestId}")
    public Call<GetContestDetailsResponse> getContestDetails(@Path("contestId") String contestId);



    @GET("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest();
}
