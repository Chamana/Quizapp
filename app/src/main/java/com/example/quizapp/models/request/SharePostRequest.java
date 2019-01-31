package com.example.quizapp.models.request;

import com.google.gson.annotations.SerializedName;

public class SharePostRequest{

	@SerializedName("postId")
	private String postId;

	@SerializedName("destinationId")
	private String destinationId;

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setDestinationId(String destinationId){
		this.destinationId = destinationId;
	}

	public String getDestinationId(){
		return destinationId;
	}

	@Override
 	public String toString(){
		return 
			"SharePostRequest{" + 
			"postId = '" + postId + '\'' + 
			",destinationId = '" + destinationId + '\'' + 
			"}";
		}
}