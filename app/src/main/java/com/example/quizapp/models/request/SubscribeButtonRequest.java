package com.example.quizapp.models.request;


import com.google.gson.annotations.SerializedName;

public class SubscribeButtonRequest{

	@SerializedName("response")
	private Response response;

	@SerializedName("errorMessage")
	private String errorMessage;

	@SerializedName("status")
	private String status;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
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
			"SubscribeButtonRequest{" + 
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}