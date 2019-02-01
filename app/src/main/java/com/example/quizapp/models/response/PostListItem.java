package com.example.quizapp.models.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PostListItem{

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

    public long getDate() {
        return date;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public List<PostsCommentsItem> getPostsComments() {
        return postsComments;
    }

    public String getPostId() {
        return postId;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getPostLikes() {
        return postLikes;
    }

    @SerializedName("userId")
	private String userId;

	@SerializedName("url")
	private String url;

	@SerializedName("postLikes")
	private List<String> postLikes;

	public void setDate(long date){
		this.date = date;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setPostsComments(List<PostsCommentsItem> postsComments){
		this.postsComments = postsComments;
	}

	public void setPostId(String postId){
		this.postId = postId;
	}

	public void setType(String type){
		this.type = type;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public void setPostLikes(List<String> postLikes){
		this.postLikes = postLikes;
	}

	@Override
 	public String toString(){
		return 
			"PostListItem{" + 
			"date = '" + date + '\'' + 
			",createdBy = '" + createdBy + '\'' + 
			",description = '" + description + '\'' + 
			",postsComments = '" + postsComments + '\'' + 
			",postId = '" + postId + '\'' + 
			",type = '" + type + '\'' + 
			",userId = '" + userId + '\'' + 
			",url = '" + url + '\'' + 
			",postLikes = '" + postLikes + '\'' + 
			"}";
		}
}