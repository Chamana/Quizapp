package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;


public class NotificationTokenResponse{

	@SerializedName("androidDeviceId")
	private String androidDeviceId;

	@SerializedName("browserDeviceId")
	private String browserDeviceId;

	@SerializedName("facebookPreference")
	private int facebookPreference;

	@SerializedName("emailId")
	private String emailId;

	@SerializedName("contestPreference")
	private int contestPreference;

	@SerializedName("userName")
	private String userName;

	@SerializedName("message")
	private String message;

	@SerializedName("userId")
	private String userId;

	@SerializedName("status")
	private String status;

	public void setAndroidDeviceId(String androidDeviceId){
		this.androidDeviceId = androidDeviceId;
	}

	public String getAndroidDeviceId(){
		return androidDeviceId;
	}

	public void setBrowserDeviceId(String browserDeviceId){
		this.browserDeviceId = browserDeviceId;
	}

	public String getBrowserDeviceId(){
		return browserDeviceId;
	}

	public void setFacebookPreference(int facebookPreference){
		this.facebookPreference = facebookPreference;
	}

	public int getFacebookPreference(){
		return facebookPreference;
	}

	public void setEmailId(String emailId){
		this.emailId = emailId;
	}

	public String getEmailId(){
		return emailId;
	}

	public void setContestPreference(int contestPreference){
		this.contestPreference = contestPreference;
	}

	public int getContestPreference(){
		return contestPreference;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"NotificationTokenResponse{" + 
			"androidDeviceId = '" + androidDeviceId + '\'' + 
			",browserDeviceId = '" + browserDeviceId + '\'' + 
			",facebookPreference = '" + facebookPreference + '\'' + 
			",emailId = '" + emailId + '\'' + 
			",contestPreference = '" + contestPreference + '\'' + 
			",userName = '" + userName + '\'' + 
			",message = '" + message + '\'' + 
			",userId = '" + userId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}