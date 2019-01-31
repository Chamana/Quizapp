package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("difficulty")
	private String difficulty;

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("subscribed")
	private boolean subscribed;

	@SerializedName("skips")
	private int skips;

	@SerializedName("noOfQuestions")
	private int noOfQuestions;

	@SerializedName("name")
	private String name;

	@SerializedName("active")
	private boolean active;

	@SerializedName("type")
	private String type;

	@SerializedName("categoryId")
	private String categoryId;

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

	public void setSubscribed(boolean subscribed){
		this.subscribed = subscribed;
	}

	public boolean isSubscribed(){
		return subscribed;
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

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"difficulty = '" + difficulty + '\'' + 
			",contestId = '" + contestId + '\'' + 
			",subscribed = '" + subscribed + '\'' + 
			",skips = '" + skips + '\'' + 
			",noOfQuestions = '" + noOfQuestions + '\'' + 
			",name = '" + name + '\'' + 
			",active = '" + active + '\'' + 
			",type = '" + type + '\'' + 
			",categoryId = '" + categoryId + '\'' + 
			"}";
		}
}