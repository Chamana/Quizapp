package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;


public class FollowResponseDTOList1Item{

	@SerializedName("userImageURL")
	private String userImageURL;

	@SerializedName("userId")
	private String userId;

	@SerializedName("username")
	private String username;

	public void setUserImageURL(String userImageURL){
		this.userImageURL = userImageURL;
	}

	public String getUserImageURL(){
		return userImageURL;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"FollowResponseDTOList1Item{" + 
			"userImageURL = '" + userImageURL + '\'' + 
			",userId = '" + userId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}