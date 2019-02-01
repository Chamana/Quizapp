package com.example.quizapp.models.request;



import com.google.gson.annotations.SerializedName;


public class UserGetAllContestRequest {

    @SerializedName("userId")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return
                "UserGetAllContestRequest{" +
                        "userId = '" + userId + '\'' +
                        "}";
    }
}