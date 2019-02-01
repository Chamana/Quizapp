package com.example.quizapp.models.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class AdvertisementResponse{

	@SerializedName("response")
	private List<ResponseItem> response;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<ResponseItem> getResponse(){
		return response;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"AdvertisementResponse{" + 
			"response = '" + response + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}