package com.example.quizapp.models.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class PostsCommentsItem{

	@SerializedName("commentId")
	private String commentId;

	@SerializedName("description")
	private String description;

	@SerializedName("userId")
	private String userId;

	@SerializedName("nestedPostComments")
	private List<NestedPostCommentsItem> nestedPostComments;

	@SerializedName("username")
	private String username;

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public void setNestedPostComments(List<NestedPostCommentsItem> nestedPostComments){
		this.nestedPostComments = nestedPostComments;
	}

	public void setUsername(String username){
		this.username = username;
	}

	@Override
 	public String toString(){
		return 
			"PostsCommentsItem{" + 
			"commentId = '" + commentId + '\'' + 
			",description = '" + description + '\'' + 
			",userId = '" + userId + '\'' + 
			",nestedPostComments = '" + nestedPostComments + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}