package com.example.quizapp.api;

import com.example.quizapp.models.response.GetAllContestResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IConnectAPI {

    @GET("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest();
}
