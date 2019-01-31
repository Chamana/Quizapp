package com.example.quizapp.models.request;

public class PutSubmitQuestion{
	private Object response;
	private String errorMessage;
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
			"PutSubmitQuestion{" + 
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
