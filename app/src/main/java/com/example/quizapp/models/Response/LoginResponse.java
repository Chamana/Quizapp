package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("Authorization")
	private String authorization;

	public void setAuthorization(String authorization){
		this.authorization = authorization;
	}

	public String getAuthorization(){
		return authorization;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"authorization = '" + authorization + '\'' + 
			"}";
		}
}