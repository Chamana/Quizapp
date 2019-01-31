package com.example.quizapp.models.Response;

public class SubmitResponse {
	private int score;
	private Contest contest;
	private boolean finished;
	private String userId;
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
			"SubmitResponse{" +
			"score = '" + score + '\'' + 
			",contest = '" + contest + '\'' + 
			",finished = '" + finished + '\'' + 
			",userId = '" + userId + '\'' + 
			",contestsubscribedId = '" + contestsubscribedId + '\'' + 
			"}";
		}
}
