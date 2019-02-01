package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;

public class SignupResponse{

	@SerializedName("password")
	private String password;

	@SerializedName("role")
	private String role;

	@SerializedName("name")
	private String name;

	@SerializedName("userName")
	private String userName;

	@SerializedName("message")
	private String message;

	@SerializedName("userId")
	private String userId;

	@SerializedName("token")
	private String token;

	@SerializedName("status")
	private boolean status;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
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
			"SignupResponse{" + 
			"password = '" + password + '\'' + 
			",role = '" + role + '\'' + 
			",name = '" + name + '\'' + 
			",userName = '" + userName + '\'' + 
			",message = '" + message + '\'' + 
			",userId = '" + userId + '\'' + 
			",token = '" + token + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}