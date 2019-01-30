package com.example.quizapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("response")
	private Response response;

	@SerializedName("errorMessage")
	private String errorMessage;

	@SerializedName("status")
	private String status;

	@SerializedName("difficulty")
	private String difficulty;

	@SerializedName("duration")
	private int duration;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("name")
	private String name;

	@SerializedName("mediaType")
	private String mediaType;

	@SerializedName("optionDTOList")
	private List<OptionDTOListItem> optionDTOList;

	@SerializedName("category")
	private String category;

	@SerializedName("ansType")
	private String ansType;

	@SerializedName("content")
	private String content;

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

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getDuration(){
		return duration;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMediaType(String mediaType){
		this.mediaType = mediaType;
	}

	public String getMediaType(){
		return mediaType;
	}

	public void setOptionDTOList(List<OptionDTOListItem> optionDTOList){
		this.optionDTOList = optionDTOList;
	}

	public List<OptionDTOListItem> getOptionDTOList(){
		return optionDTOList;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setAnsType(String ansType){
		this.ansType = ansType;
	}

	public String getAnsType(){
		return ansType;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"response = '" + response + '\'' + 
			",errorMessage = '" + errorMessage + '\'' + 
			",status = '" + status + '\'' + 
			",difficulty = '" + difficulty + '\'' + 
			",duration = '" + duration + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",name = '" + name + '\'' + 
			",mediaType = '" + mediaType + '\'' + 
			",optionDTOList = '" + optionDTOList + '\'' + 
			",category = '" + category + '\'' + 
			",ansType = '" + ansType + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}