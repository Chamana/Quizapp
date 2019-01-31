package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;

public class ResponseItem{

	@SerializedName("subscribed")
	private boolean subscribed;

	@SerializedName("type")
	private String type;

	@SerializedName("name")
	private String name;

	@SerializedName("active")
	private boolean active;

	@SerializedName("categoryId")
	private String categoryId;

	@SerializedName("difficulty")
	private String difficulty;

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("skips")
	private int skips;

	@SerializedName("noOfQuestions")
	private int noOfQuestions;

	public void setSubscribed(boolean subscribed){
		this.subscribed = subscribed;
	}

	public boolean isSubscribed(){
		return subscribed;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setDifficulty(String difficulty){
		this.difficulty = difficulty;
	}

	public String getDifficulty(){
		return difficulty;
	}

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setSkips(int skips){
		this.skips = skips;
	}

	public int getSkips(){
		return skips;
	}

	public void setNoOfQuestions(int noOfQuestions){
		this.noOfQuestions = noOfQuestions;
	}

	public int getNoOfQuestions(){
		return noOfQuestions;
	}

	@Override
 	public String toString(){
		return 
			"ResponseItem{" + 
			"“subscribed” = '" + subscribed + '\'' + 
			",“type” = '" + type + '\'' + 
			",“name” = '" + name + '\'' + 
			",“active” = '" + active + '\'' + 
			",“categoryId” = '" + categoryId + '\'' + 
			",“difficulty” = '" + difficulty + '\'' + 
			",“contestId” = '" + contestId + '\'' + 
			",“skips” = '" + skips + '\'' + 
			",“noOfQuestions” = '" + noOfQuestions + '\'' + 
			"}";
		}
}