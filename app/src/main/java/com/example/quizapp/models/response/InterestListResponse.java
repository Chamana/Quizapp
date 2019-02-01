package com.example.quizapp.models.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class InterestListResponse{

	@SerializedName("interests")
	private List<String> interests;

	public void setInterests(List<String> interests){
		this.interests = interests;
	}

	public List<String> getInterests(){
		return interests;
	}

	@Override
 	public String toString(){
		return 
			"InterestListResponse{" + 
			"interests = '" + interests + '\'' + 
			"}";
		}
}