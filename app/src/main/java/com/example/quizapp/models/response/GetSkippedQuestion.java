package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;

public class GetSkippedQuestion{

	@SerializedName("response")
	private QuesResponse response;

	@SerializedName("errorMessage")
	private String errorMessage;

	@SerializedName("status")
	private String status;

	public void setQuesResponse(QuesResponse quesResponse){
		this.response = quesResponse;
	}

	public QuesResponse getQuesResponse(){
		return response;
	}

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"GetSkippedQuestion{" + 
			"quesResponse = '" + response + '\'' +
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}