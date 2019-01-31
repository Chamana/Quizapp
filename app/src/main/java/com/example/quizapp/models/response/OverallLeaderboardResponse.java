package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;

public class OverallLeaderboardResponse{

	@SerializedName("score")
	private double score;

	@SerializedName("totalContestsPlayed")
	private int totalContestsPlayed;

	@SerializedName("userName")
	private String userName;

	@SerializedName("userId")
	private String userId;

	public void setScore(double score){
		this.score = score;
	}

	public double getScore(){
		return score;
	}

	public void setTotalContestsPlayed(int totalContestsPlayed){
		this.totalContestsPlayed = totalContestsPlayed;
	}

	public int getTotalContestsPlayed(){
		return totalContestsPlayed;
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
			"OverallLeaderboardResponse{" + 
			"score = '" + score + '\'' + 
			",totalContestsPlayed = '" + totalContestsPlayed + '\'' + 
			",userName = '" + userName + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}
}