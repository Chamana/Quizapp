package com.example.quizapp.api;

import com.example.quizapp.models.request.PutSkippedQuestion;
import com.example.quizapp.models.request.PutSubmitQuestion;
import com.example.quizapp.models.request.SubmitQuesBody;
import com.example.quizapp.models.response.GetContestQuestion;
import com.example.quizapp.models.response.GetContestQuestionBody;
import com.example.quizapp.models.response.GetSkippedQuestion;
import com.example.quizapp.models.response.SubmitQuesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import com.example.quizapp.models.Response.GetAllContestResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IConnectAPI {

    @GET("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest();



    @POST("/contests/{contestId}/play/nextQuestion")
    public Call<GetContestQuestion> getContestQuestion(@Path(value = "contestId") String contestId, @Body GetContestQuestionBody getContestQuestionBody);

    @PUT("/contests/{contestId}/play/{questionId}/skip")
    public Call<PutSkippedQuestion> putSkippedQuestion(@Path(value = "contestId") String contestId, @Path(value = "questionId") String questionId, @Body GetContestQuestionBody getContestQuestionBody);

    @POST("/contests/{contestId}/play/skippedQuestion")
    public Call<GetSkippedQuestion> getSkippedQuestion(@Path(value = "contestId") String contestId, @Body GetContestQuestionBody getContestQuestionBody);

    @PUT("/contests/{contestId}/play/{questionId}/stop")
    public Call<PutSubmitQuestion> putSubmitQuestion(@Path(value = "contestId") String contestId, @Path(value = "questionId") String questionId, @Body SubmitQuesBody submitQuesBody);

    @POST("/contests/{contestId}/play/submit")
    public Call<SubmitQuesResponse> submitContest(@Path(value = "contestId")String contestId, @Body GetContestQuestionBody getContestQuestionBody);

}