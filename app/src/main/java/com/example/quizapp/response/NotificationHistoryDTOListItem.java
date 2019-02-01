package com.example.quizapp.response;

import com.google.gson.annotations.SerializedName;

public class NotificationHistoryDTOListItem{

	@SerializedName("timeStamp")
	private String timeStamp;

	@SerializedName("read")
	private boolean read;

	@SerializedName("notificationId")
	private String notificationId;

	@SerializedName("notificationType")
	private String notificationType;

	@SerializedName("message")
	private String message;

	@SerializedName("url")
	private String url;

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
	}

	public void setRead(boolean read){
		this.read = read;
	}

	public boolean isRead(){
		return read;
	}

	public void setNotificationId(String notificationId){
		this.notificationId = notificationId;
	}

	public String getNotificationId(){
		return notificationId;
	}

	public void setNotificationType(String notificationType){
		this.notificationType = notificationType;
	}

	public String getNotificationType(){
		return notificationType;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	@Override
 	public String toString(){
		return 
			"NotificationHistoryDTOListItem{" + 
			"timeStamp = '" + timeStamp + '\'' + 
			",read = '" + read + '\'' + 
			",notificationId = '" + notificationId + '\'' + 
			",notificationType = '" + notificationType + '\'' + 
			",message = '" + message + '\'' + 
			",url = '" + url + '\'' + 
			"}";
		}
}