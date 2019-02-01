package com.example.quizapp.models.Response;

import com.google.gson.annotations.SerializedName;

public class CurrentLeaderBoardResponse{

	@SerializedName("contestId")
	private String contestId;

	@SerializedName("score")
	private int score;

	@SerializedName("contestName")
	private String contestName;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;

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

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
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
			"CurrentLeaderBoardResponse{" + 
			"contestId = '" + contestId + '\'' + 
			",score = '" + score + '\'' + 
			",contestName = '" + contestName + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}