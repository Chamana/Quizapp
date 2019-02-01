package com.example.quizapp.response;

import com.google.gson.annotations.SerializedName;

public class FeedsListItem{

	@SerializedName("image")
	private String image;

	@SerializedName("post")
	private Post post;

	@SerializedName("userId")
	private String userId;

	@SerializedName("username")
	private String username;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPost(Post post){
		this.post = post;
	}

	public Post getPost(){
		return post;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"FeedsListItem{" + 
			"image = '" + image + '\'' + 
			",post = '" + post + '\'' + 
			",userId = '" + userId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}