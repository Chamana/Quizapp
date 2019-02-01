package com.example.quizapp.models.Response.dynamicContest;

public class DynamicContestResponse{
	private Response response;
	private String errorMessage;
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
			"DynamicContestResponse{" + 
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
