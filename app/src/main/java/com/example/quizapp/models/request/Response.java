package com.example.quizapp.models.request;


import com.google.gson.annotations.SerializedName;


public class Response{

	@SerializedName("score")
	private int score;

	@SerializedName("contest")
	private Contest contest;

	@SerializedName("finished")
	private boolean finished;

	@SerializedName("userId")
	private String userId;

	@SerializedName("contestsubscribedId")
	private String contestsubscribedId;

	public void setScore(int score){
		this.score = score;
	}

	public int getScore(){
		return score;
	}

	public void setContest(Contest contest){
		this.contest = contest;
	}

	public Contest getContest(){
		return contest;
	}

	public void setFinished(boolean finished){
		this.finished = finished;
	}

	public boolean isFinished(){
		return finished;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setContestsubscribedId(String contestsubscribedId){
		this.contestsubscribedId = contestsubscribedId;
	}

	public String getContestsubscribedId(){
		return contestsubscribedId;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"score = '" + score + '\'' + 
			",contest = '" + contest + '\'' + 
			",finished = '" + finished + '\'' + 
			",userId = '" + userId + '\'' + 
			",contestsubscribedId = '" + contestsubscribedId + '\'' + 
			"}";
		}
}