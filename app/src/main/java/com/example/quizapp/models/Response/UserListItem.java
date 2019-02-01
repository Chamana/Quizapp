package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;

public class UserListItem{

	@SerializedName("userImageURL")
	private String userImageURL;

	@SerializedName("interest")
	private String interest;

	@SerializedName("about")
	private String about;

	@SerializedName("dateOfBirth")
	private long dateOfBirth;

	@SerializedName("userId")
	private String userId;

	@SerializedName("username")
	private String username;

	public UserListItem() {
	}

	public UserListItem(String userImageURL, String interest, String about, long dateOfBirth, String userId, String username) {
		this.userImageURL = userImageURL;
		this.interest = interest;
		this.about = about;
		this.dateOfBirth = dateOfBirth;
		this.userId = userId;
		this.username = username;
	}

	public void setUserImageURL(String userImageURL){
		this.userImageURL = userImageURL;
	}

	public String getUserImageURL(){
		return userImageURL;
	}

	public void setInterest(String interest){
		this.interest = interest;
	}

	public String getInterest(){
		return interest;
	}

	public void setAbout(String about){
		this.about = about;
	}

	public String getAbout(){
		return about;
	}

	public void setDateOfBirth(long dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}

	public long getDateOfBirth(){
		return dateOfBirth;
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
			"UserListItem{" + 
			"userImageURL = '" + userImageURL + '\'' + 
			",interest = '" + interest + '\'' + 
			",about = '" + about + '\'' + 
			",dateOfBirth = '" + dateOfBirth + '\'' + 
			",userId = '" + userId + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}