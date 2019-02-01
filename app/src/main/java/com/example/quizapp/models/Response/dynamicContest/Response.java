package com.example.quizapp.models.Response.dynamicContest;

public class Response{
	private String contestId;
	private boolean active;
	private Long startTime;
	private String contestName;
	private String type;

	public void setContestId(String contestId){
		this.contestId = contestId;
	}

	public String getContestId(){
		return contestId;
	}

	public void setActive(boolean active){
		this.active = active;
	}

	public boolean isActive(){
		return active;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public void setContestName(String contestName){
		this.contestName = contestName;
	}

	public String getContestName(){
		return contestName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"contestId = '" + contestId + '\'' + 
			",active = '" + active + '\'' + 
			",startTime = '" + startTime + '\'' + 
			",contestName = '" + contestName + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}
