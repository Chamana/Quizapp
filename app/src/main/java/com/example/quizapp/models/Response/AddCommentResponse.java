package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;


public class AddCommentResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("statusCode")
	private int statusCode;

	@SerializedName("status")
	private boolean status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
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
			"AddCommentResponse{" + 
			"message = '" + message + '\'' + 
			",statusCode = '" + statusCode + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}