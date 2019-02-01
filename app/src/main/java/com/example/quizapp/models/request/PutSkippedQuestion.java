package com.example.quizapp.models.request;

import com.google.gson.annotations.SerializedName;

public class PutSkippedQuestion{

	@SerializedName("response")
	private Object response;

	@SerializedName("errorMessage")
	private String errorMessage;

	@SerializedName("status")
	private String status;

	public void setResponse(Object response){
		this.response = response;
	}

	public Object getResponse(){
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
			"PutSkippedQuestion{" + 
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}