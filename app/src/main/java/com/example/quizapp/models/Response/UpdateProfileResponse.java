package com.example.quizapp.models.Response;


import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse{

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private boolean status;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UpdateProfileResponse{" + 
			"user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}