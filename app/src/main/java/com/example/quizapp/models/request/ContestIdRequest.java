package com.example.quizapp.models.request;


import com.google.gson.annotations.SerializedName;

public class ContestIdRequest{

	@SerializedName("userId")
	private String userId;

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"ContestIdRequest{" + 
			"userId = '" + userId + '\'' + 
			"}";
		}
}