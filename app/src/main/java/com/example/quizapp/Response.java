package com.example.quizapp;

public class Response{
	private Response response;
	private String errorMessage;
	private String status;
	private String difficulty;
	private String contestId;
	private boolean subscribed;
	private int skips;
	private int noOfQuestions;
	private String name;
	private boolean active;
	private String type;
	private String categoryId;

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
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
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			",difficulty = '" + difficulty + '\'' + 
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
