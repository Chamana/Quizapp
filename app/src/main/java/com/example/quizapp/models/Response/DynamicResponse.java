package com.example.quizapp.models.Response;

public class DynamicResponse{
	private String response;
	private String errorMessage;
	private String status;

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
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
			"DynamicResponse{" + 
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
