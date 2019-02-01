package com.example.quizapp.models.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class SearchResponse{

	@SerializedName("userList")
	private List<UserListItem> userList;

	public void setUserList(List<UserListItem> userList){
		this.userList = userList;
	}

	public List<UserListItem> getUserList(){
		return userList;
	}

	@Override
 	public String toString(){
		return 
			"SearchResponse{" + 
			"userList = '" + userList + '\'' + 
			"}";
		}
}