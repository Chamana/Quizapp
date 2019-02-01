package com.example.quizapp.models.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UserFeedResponse{

	@SerializedName("postList")
	private List<PostListItem> postList;

	public List<PostListItem> getPostList() {
		return postList;
	}

	public void setPostList(List<PostListItem> postList){
		this.postList = postList;
	}

	@Override
 	public String toString(){
		return 
			"UserFeedResponse{" + 
			"postList = '" + postList + '\'' + 
			"}";
		}
}