package com.example.quizapp.models.Response;


import com.example.quizapp.models.Response.contestDetails.ResponseItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllContestResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("response")
    private List<ResponseItem> response;

    @SerializedName("errorMessage")
    private String errorMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResponseItem> getResponse() {
        return response;
    }

    public void setResponse(List<ResponseItem> response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return
                "GetAllContestResponse{" +
                        "“status” = '" + status + '\'' +
                        ",“response” = '" + response + '\'' +
                        ",“errorMessage” = '" + errorMessage + '\'' +
                        "}";
    }
}