package com.example.quizapp.models.response;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class UserProfileResponse{

	@SerializedName("userImageURL")
	private String userImageURL;

	@SerializedName("interest")
	private String interest;

	@SerializedName("followResponseFollowerList")
	private List<FollowResponseFollowerListItem> followResponseFollowerList;

	@SerializedName("about")
	private String about;

	@SerializedName("dateOfBirth")
	private long dateOfBirth;

	@SerializedName("followResponseDTOList1")
	private List<FollowResponseDTOList1Item> followResponseDTOList1;

	@SerializedName("username")
	private String username;

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

	public void setFollowResponseFollowerList(List<FollowResponseFollowerListItem> followResponseFollowerList){
		this.followResponseFollowerList = followResponseFollowerList;
	}

	public List<FollowResponseFollowerListItem> getFollowResponseFollowerList(){
		return followResponseFollowerList;
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

	public void setFollowResponseDTOList1(List<FollowResponseDTOList1Item> followResponseDTOList1){
		this.followResponseDTOList1 = followResponseDTOList1;
	}

	public List<FollowResponseDTOList1Item> getFollowResponseDTOList1(){
		return followResponseDTOList1;
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
			"UserProfileResponse{" + 
			"userImageURL = '" + userImageURL + '\'' + 
			",interest = '" + interest + '\'' + 
			",followResponseFollowerList = '" + followResponseFollowerList + '\'' + 
			",about = '" + about + '\'' + 
			",dateOfBirth = '" + dateOfBirth + '\'' + 
			",followResponseDTOList1 = '" + followResponseDTOList1 + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}