package com.example.quizapp.models.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class QuesResponse {

	@SerializedName("duration")
	private int duration;

	@SerializedName("questionContent")
	private String questionContent;

	@SerializedName("questionId")
	private String questionId;

	@SerializedName("questionCategory")
	private String questionCategory;

	@SerializedName("answerType")
	private String answerType;

	@SerializedName("optionDTOList")
	private List<OptionDTOListItem> optionDTOList;

	@SerializedName("questionDifficulty")
	private String questionDifficulty;

	@SerializedName("questionName")
	private String questionName;

	@SerializedName("questionType")
	private String questionType;

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getDuration(){
		return duration;
	}

	public void setQuestionContent(String questionContent){
		this.questionContent = questionContent;
	}

	public String getQuestionContent(){
		return questionContent;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setQuestionCategory(String questionCategory){
		this.questionCategory = questionCategory;
	}

	public String getQuestionCategory(){
		return questionCategory;
	}

	public void setAnswerType(String answerType){
		this.answerType = answerType;
	}

	public String getAnswerType(){
		return answerType;
	}

	public void setOptionDTOList(List<OptionDTOListItem> optionDTOList){
		this.optionDTOList = optionDTOList;
	}

	public List<OptionDTOListItem> getOptionDTOList(){
		return optionDTOList;
	}

	public void setQuestionDifficulty(String questionDifficulty){
		this.questionDifficulty = questionDifficulty;
	}

	public String getQuestionDifficulty(){
		return questionDifficulty;
	}

	public void setQuestionName(String questionName){
		this.questionName = questionName;
	}

	public String getQuestionName(){
		return questionName;
	}

	public void setQuestionType(String questionType){
		this.questionType = questionType;
	}

	public String getQuestionType(){
		return questionType;
	}

	@Override
 	public String toString(){
		return 
			"QuesResponse{" +
			"duration = '" + duration + '\'' + 
			",questionContent = '" + questionContent + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",questionCategory = '" + questionCategory + '\'' + 
			",answerType = '" + answerType + '\'' + 
			",optionDTOList = '" + optionDTOList + '\'' + 
			",questionDifficulty = '" + questionDifficulty + '\'' + 
			",questionName = '" + questionName + '\'' + 
			",questionType = '" + questionType + '\'' + 
			"}";
		}
}