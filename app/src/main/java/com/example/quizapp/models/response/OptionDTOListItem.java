package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;

public class OptionDTOListItem{

	@SerializedName("optionContent")
	private String optionContent;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("optionId")
	private String optionId;

	public void setOptionContent(String optionContent){
		this.optionContent = optionContent;
	}

	public String getOptionContent(){
		return optionContent;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setOptionId(String optionId){
		this.optionId = optionId;
	}

	public String getOptionId(){
		return optionId;
	}

	@Override
 	public String toString(){
		return 
			"OptionDTOListItem{" + 
			"optionContent = '" + optionContent + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",optionId = '" + optionId + '\'' + 
			"}";
		}
}