package com.example.quizapp.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class HomeFeedResponse{

	@SerializedName("feedsList")
	private List<FeedsListItem> feedsList;

	public void setFeedsList(List<FeedsListItem> feedsList){
		this.feedsList = feedsList;
	}

	public List<FeedsListItem> getFeedsList(){
		return feedsList;
	}

	@Override
 	public String toString(){
		return 
			"HomeFeedResponse{" + 
			"feedsList = '" + feedsList + '\'' + 
			"}";
		}
}