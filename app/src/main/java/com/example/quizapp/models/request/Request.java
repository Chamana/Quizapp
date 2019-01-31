package com.example.quizapp.models.request;

public class Request{
	private String questionId;
	private int questionSequence;
	private String optionIds;

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setQuestionSequence(int questionSequence){
		this.questionSequence = questionSequence;
	}

	public int getQuestionSequence(){
		return questionSequence;
	}

	public void setOptionIds(String optionIds){
		this.optionIds = optionIds;
	}

	public String getOptionIds(){
		return optionIds;
	}

	public Request(String questionId, int questionSequence, String optionIds) {
		this.questionId = questionId;
		this.questionSequence = questionSequence;
		this.optionIds = optionIds;
	}

	@Override
 	public String toString(){
		return 
			"Request{" + 
			"questionId = '" + questionId + '\'' + 
			",questionSequence = '" + questionSequence + '\'' + 
			",optionIds = '" + optionIds + '\'' + 
			"}";
		}
}
