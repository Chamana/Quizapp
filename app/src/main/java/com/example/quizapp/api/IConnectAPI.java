package com.example.quizapp.api;

import com.example.quizapp.models.request.UserGetAllContestRequest;
import com.example.quizapp.models.response.GetAllContestResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IConnectAPI {

    @POST("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest(@Body UserGetAllContestRequest userGetAllContestRequest);
}
