package com.example.quizapp.models.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GetAllContestResponse{

	@SerializedName("status")
	private String status;

	@SerializedName("response")
	private List<ResponseItem> response;

	@SerializedName("errorMessage")
	private String errorMessage;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setResponse(List<ResponseItem> response){
		this.response = response;
	}

	public List<com.example.quizapp.models.response.ResponseItem> getResponse(){
		return response;
	}

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	@Override
 	public String toString(){
		return 
			"GetAllContestResponse{" +
			"“status” = '" + status + '\'' + 
			",“response” = '" + response + '\'' + 
			",“errorMessage” = '" + errorMessage + '\'' + 
			"}";
		}
}