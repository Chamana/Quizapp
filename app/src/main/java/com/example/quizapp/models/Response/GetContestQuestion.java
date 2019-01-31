package com.example.quizapp.models.response;

public class GetContestQuestion{
	private QuesResponse response;
	private String errorMessage;
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
			"GetContestQuestion{" + 
			"quesResponse = '" + response + '\'' +
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}
