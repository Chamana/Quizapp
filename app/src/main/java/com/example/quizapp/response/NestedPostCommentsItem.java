package com.example.quizapp.response;

import com.google.gson.annotations.SerializedName;

public class NestedPostCommentsItem{

	@SerializedName("nestedCommentId")
	private String nestedCommentId;

	@SerializedName("reply")
	private String reply;

	@SerializedName("userId")
	private String userId;

	@SerializedName("username")
	private String username;

	public void setNestedCommentId(String nestedCommentId){
		this.nestedCommentId = nestedCommentId;
	}

	public String getNestedCommentId(){
		return nestedCommentId;
	}

	public void setReply(String reply){
		this.reply = reply;
	}

	public String getReply(){
		return reply;
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
			"NestedPostCommentsItem{" + 
			"nestedCommentId = '" + nestedCommentId + '\'' + 
			",reply = '" + reply + '\'' + 
			",userId = '" + userId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}