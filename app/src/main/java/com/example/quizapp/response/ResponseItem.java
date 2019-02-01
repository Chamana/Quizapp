package com.example.quizapp.response;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("advertisementName")
	private String advertisementName;

	@SerializedName("clickURL")
	private String clickURL;

	@SerializedName("advertisementId")
	private String advertisementId;

	@SerializedName("advertisementDescription")
	private String advertisementDescription;

	@SerializedName("imageURL")
	private String imageURL;

	@SerializedName("category")
	private String category;

	public void setAdvertisementName(String advertisementName){
		this.advertisementName = advertisementName;
	}

	public String getAdvertisementName(){
		return advertisementName;
	}

	public void setClickURL(String clickURL){
		this.clickURL = clickURL;
	}

	public String getClickURL(){
		return clickURL;
	}

	public void setAdvertisementId(String advertisementId){
		this.advertisementId = advertisementId;
	}

	public String getAdvertisementId(){
		return advertisementId;
	}

	public void setAdvertisementDescription(String advertisementDescription){
		this.advertisementDescription = advertisementDescription;
	}

	public String getAdvertisementDescription(){
		return advertisementDescription;
	}

	public void setImageURL(String imageURL){
		this.imageURL = imageURL;
	}

	public String getImageURL(){
		return imageURL;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"advertisementName = '" + advertisementName + '\'' + 
			",clickURL = '" + clickURL + '\'' + 
			",advertisementId = '" + advertisementId + '\'' + 
			",advertisementDescription = '" + advertisementDescription + '\'' + 
			",imageURL = '" + imageURL + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}