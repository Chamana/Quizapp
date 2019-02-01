package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;

public class GetContestQuestionBody{

	@SerializedName("userId")
	private String userId;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public GetContestQuestionBody(String userId) {
		this.userId = userId;
	}

	@Override
 	public String toString(){
		return 
			"GetContestQuestionBody{" + 
			"userId = '" + userId + '\'' + 
			"}";
		}
}