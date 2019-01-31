package com.example.quizapp.models.request;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class UserGetAllContestRequest{

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
			"UserGetAllContestRequest{" + 
			"userId = '" + userId + '\'' + 
			"}";
		}
}