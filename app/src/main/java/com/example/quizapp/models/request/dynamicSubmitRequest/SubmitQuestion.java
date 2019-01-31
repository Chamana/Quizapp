package com.example.quizapp.models.request.dynamicSubmitRequest;

public class SubmitQuestion{
	private Request request;
	private String userId;

	public SubmitQuestion(Request request, String userId) {
		this.request = request;
		this.userId = userId;
	}

	public void setRequest(Request request){
		this.request = request;
	}

	public Request getRequest(){
		return request;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"SubmitQuestion{" + 
			"request = '" + request + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
