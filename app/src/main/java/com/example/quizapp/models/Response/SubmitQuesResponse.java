package com.example.quizapp.models.Response;

public class SubmitQuesResponse{
	private SubmitResponse response;
	private String errorMessage;
	private String status;

	public void setSubmitResponse(SubmitResponse submitResponse){
		this.response = submitResponse;
	}

	public SubmitResponse getSubmitResponse(){
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
			"SubmitQuesResponse{" + 
			"submitResponse = '" + response + '\'' +
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
