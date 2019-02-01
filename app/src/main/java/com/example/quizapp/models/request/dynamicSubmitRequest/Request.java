package com.example.quizapp.models.request.dynamicSubmitRequest;

public class Request{
	private String contestId;
	private int score;
	private String questionId;
	private String answer;
	private String questionSequence;
	private int startTime;
	private int endTime;
	private String contestPlayAreaId;
	private String userId;

	public Request(String contestId, int score, String questionId, String answer, String questionSequence, int startTime, int endTime, String contestPlayAreaId, String userId) {
		this.contestId = contestId;
		this.score = score;
		this.questionId = questionId;
		this.answer = answer;
		this.questionSequence = questionSequence;
		this.startTime = startTime;
		this.endTime = endTime;
		this.contestPlayAreaId = contestPlayAreaId;
		this.userId = userId;
	}

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setQuestionId(String questionId){
		this.questionId = questionId;
	}

	public String getQuestionId(){
		return questionId;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	public void setQuestionSequence(String questionSequence){
		this.questionSequence = questionSequence;
	}

	public String getQuestionSequence(){
		return questionSequence;
	}

	public void setStartTime(int startTime){
		this.startTime = startTime;
	}

	public int getStartTime(){
		return startTime;
	}

	public void setEndTime(int endTime){
		this.endTime = endTime;
	}

	public int getEndTime(){
		return endTime;
	}

	public void setContestPlayAreaId(String contestPlayAreaId){
		this.contestPlayAreaId = contestPlayAreaId;
	}

	public String getContestPlayAreaId(){
		return contestPlayAreaId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"Request{" + 
			"contestId = '" + contestId + '\'' + 
			",score = '" + score + '\'' + 
			",questionId = '" + questionId + '\'' + 
			",answer = '" + answer + '\'' + 
			",questionSequence = '" + questionSequence + '\'' + 
			",startTime = '" + startTime + '\'' + 
			",endTime = '" + endTime + '\'' + 
			",contestPlayAreaId = '" + contestPlayAreaId + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}
