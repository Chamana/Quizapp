package com.example.quizapp.models.request;

public class SubmitQuesBody{
	private Request request;
	private String userId;

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

	public SubmitQuesBody(Request request, String userId) {
		this.request = request;
		this.userId = userId;
	}

	@Override
 	public String toString(){
		return 
			"SubmitQuesBody{" + 
			"request = '" + request + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
