package com.example.quizapp.models.response;


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

	public void setReply(String reply){
		this.reply = reply;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public void setUsername(String username){
		this.username = username;
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