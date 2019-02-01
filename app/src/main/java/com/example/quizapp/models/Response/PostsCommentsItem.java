package com.example.quizapp.models.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PostsCommentsItem{

	@SerializedName("userimage")
	private String userimage;

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

	public PostsCommentsItem(){

	}

	public PostsCommentsItem(String userimage, String commentId, String description, String userId, List<NestedPostCommentsItem> nestedPostComments, String username) {
		this.userimage = userimage;
		this.commentId = commentId;
		this.description = description;
		this.userId = userId;
		this.nestedPostComments = nestedPostComments;
		this.username = username;
	}

	public void setUserimage(String userimage){
		this.userimage = userimage;
	}

	public String getUserimage(){
		return userimage;
	}

	public void setCommentId(String commentId){
		this.commentId = commentId;
	}

	public String getCommentId(){
		return commentId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setNestedPostComments(List<NestedPostCommentsItem> nestedPostComments){
		this.nestedPostComments = nestedPostComments;
	}

	public List<NestedPostCommentsItem> getNestedPostComments(){
		return nestedPostComments;
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
			"PostsCommentsItem{" + 
			"userimage = '" + userimage + '\'' + 
			",commentId = '" + commentId + '\'' + 
			",description = '" + description + '\'' + 
			",userId = '" + userId + '\'' + 
			",nestedPostComments = '" + nestedPostComments + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}