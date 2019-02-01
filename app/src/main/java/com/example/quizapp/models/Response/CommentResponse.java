package com.example.quizapp.models.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class CommentResponse{

	@SerializedName("date")
	private long date;

	@SerializedName("createdBy")
	private String createdBy;

	@SerializedName("description")
	private String description;

	@SerializedName("postsComments")
	private List<PostsCommentsItem> postsComments;

	@SerializedName("postId")
	private String postId;

	@SerializedName("type")
	private String type;

	@SerializedName("category")
	private String category;

	@SerializedName("userId")
	private String userId;

	@SerializedName("url")
	private String url;

	@SerializedName("postLikes")
	private List<Object> postLikes;

	public void setDate(long date){
		this.date = date;
	}

	public long getDate(){
		return date;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setPostsComments(List<PostsCommentsItem> postsComments){
		this.postsComments = postsComments;
	}

	public List<PostsCommentsItem> getPostsComments(){
		return postsComments;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public String getPostId(){
		return postId;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setPostLikes(List<Object> postLikes){
		this.postLikes = postLikes;
	}

	public List<Object> getPostLikes(){
		return postLikes;
	}

	@Override
 	public String toString(){
		return 
			"CommentResponse{" + 
			"date = '" + date + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",description = '" + description + '\'' + 
			",postsComments = '" + postsComments + '\'' + 
			",postId = '" + postId + '\'' + 
			",type = '" + type + '\'' + 
			",category = '" + category + '\'' + 
			",userId = '" + userId + '\'' + 
			",url = '" + url + '\'' + 
			",postLikes = '" + postLikes + '\'' + 
			"}";
		}
}