package com.example.quizapp.api;

import com.example.quizapp.models.Response.dynamicContest.DynamicContestResponse;
import com.example.quizapp.models.request.CreatePostRequest;
import com.example.quizapp.models.request.LoginRequest;
import com.example.quizapp.models.request.SignupRequest;
import com.example.quizapp.models.request.UpdateProfileRequest;
import com.example.quizapp.models.Response.AddCommentResponse;
import com.example.quizapp.models.Response.CommentResponse;
import com.example.quizapp.models.Response.CreatePostResponse;
import com.example.quizapp.models.Response.InterestListResponse;
import com.example.quizapp.models.Response.LoginResponse;
import com.example.quizapp.models.Response.NotificationTokenResponse;
import com.example.quizapp.models.Response.SearchResponse;
import com.example.quizapp.models.Response.SignupResponse;
import com.example.quizapp.models.Response.UpdateProfileResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.example.quizapp.models.request.PutSkippedQuestion;
import com.example.quizapp.models.request.PutSubmitQuestion;
import com.example.quizapp.models.request.SubmitQuesBody;
import com.example.quizapp.models.request.dynamicSubmitRequest.SubmitContest;
import com.example.quizapp.models.request.dynamicSubmitRequest.SubmitQuestion;
import com.example.quizapp.models.Response.DynamicResponse;
import com.example.quizapp.models.Response.GetContestQuestion;
import com.example.quizapp.models.Response.GetContestQuestionBody;
import com.example.quizapp.models.Response.GetQuestionWinner;
import com.example.quizapp.models.Response.GetSkippedQuestion;
import com.example.quizapp.models.Response.SubmitQuesResponse;
import com.example.quizapp.models.request.SubscribeButtonRequest;
import com.example.quizapp.models.request.UserGetAllContestRequest;
import com.example.quizapp.models.Response.GetAllContestResponse;
import com.example.quizapp.models.Response.OverallLeaderboardResponse;
import com.example.quizapp.models.Response.contestDetails.GetContestDetailsResponse;

import java.util.List;

public interface IConnectAPI {


    @POST("/users/sign-up")
    Call<SignupResponse> signup(@Body SignupRequest request);

    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("/users/getData")
    Call<SignupResponse> getUserDetails(@Header(value = "Authorization") String token, @Query(value = "userName") String username);

    @PUT("/user/setAndroidDeviceId/{userId}/{androidDeviceId}")
    Call<NotificationTokenResponse> setNotificationToken(@Path("userId") String userId, @Path("androidDeviceId") String token);

    @GET("/getInterests")
    Call<InterestListResponse> getInterestList(@Query(value = "index") int index, @Query(value = "size") int size);

    @PUT("/userProfile/updateUserResp/{userId}")
    Call<UpdateProfileResponse> addInterestList(@Path(value = "userId") String userId, @Body UpdateProfileRequest request);

    @POST("/post/add")
    Call<CreatePostResponse> createPost(@Body CreatePostRequest request);

    @GET("/post/getPost/{postId}")
    Call<CommentResponse> getComments(@Path(value = "postId") String postId);

    @PUT("/post/addComment/{postId}")
    Call<AddCommentResponse> postComments(@Path(value = "postId") String postId, @Query(value = "userId") String userId, @Query(value = "userName") String userName, @Query("description") String description, @Query(value = "userImage") String userImage);

    @GET("/userProfile/getByUserName")
    Call<SearchResponse> searchByName(@Query(value = "username") String username);

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
    public Call<SubmitQuesResponse> submitContest(@Path(value = "contestId") String contestId, @Body GetContestQuestionBody getContestQuestionBody);

    @POST("/contests/{contestId}/play/submit")
    public Call<DynamicResponse> submitDynamicQuestion(@Path(value = "contestId") String contestId, @Body SubmitQuestion submitQuestion);

    @POST("/contests/{contestId}/play/finish")
    public Call<DynamicResponse> submitDynamicContest(@Path(value = "contestId") String contestId, @Body SubmitContest submitContest);

    @POST("/contests/{contestId}")
    public Call<GetContestDetailsResponse> getContestDetails(@Path("contestId") String contestId, @Body UserGetAllContestRequest userGetAllContestRequest);

    @POST("/contests/{contestId}/subscribe")
    public Call<SubscribeButtonRequest> getIfSubscribed(@Path("contestId") String contestId, @Body UserGetAllContestRequest userGetAllContestRequest);

    @POST("/contests/getAll")
    public Call<GetAllContestResponse> getAllContest(@Body UserGetAllContestRequest userGetAllContestRequest);

    @GET("/getReport/leaderboard")
    public Call<List<OverallLeaderboardResponse>> getLeaderboard();

    @GET("/contests/{contestId}/play/question/{questionId}/winner")
    public Call<GetQuestionWinner> getQuestionWinner(@Path(value = "contestId") String contestId, @Path(value = "questionId") String questionId);

    @GET("/contests/active")
    public Call<DynamicContestResponse> dynamicContestResponse();

}