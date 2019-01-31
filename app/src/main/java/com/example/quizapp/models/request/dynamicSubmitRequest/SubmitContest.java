package com.example.quizapp.models.request.dynamicSubmitRequest;

public class SubmitContest{
	private String request;
	private String userId;

	public SubmitContest(String request, String userId) {
		this.request = request;
		this.userId = userId;
	}

	public void setRequest(String request){
		this.request = request;
	}

	public String getRequest(){
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
			"SubmitContest{" + 
			"request = '" + request + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
