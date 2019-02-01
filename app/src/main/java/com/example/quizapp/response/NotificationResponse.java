package com.example.quizapp.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class NotificationResponse{

	@SerializedName("notificationHistoryDTOList")
	private List<NotificationHistoryDTOListItem> notificationHistoryDTOList;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setNotificationHistoryDTOList(List<NotificationHistoryDTOListItem> notificationHistoryDTOList){
		this.notificationHistoryDTOList = notificationHistoryDTOList;
	}

	public List<NotificationHistoryDTOListItem> getNotificationHistoryDTOList(){
		return notificationHistoryDTOList;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"NotificationResponse{" + 
			"notificationHistoryDTOList = '" + notificationHistoryDTOList + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}