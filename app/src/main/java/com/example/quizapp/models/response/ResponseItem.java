package com.example.quizapp.models.response;

import com.google.gson.annotations.SerializedName;

public class ResponseItem {

    @SerializedName("subscribed")
    private boolean subscribed;

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;

    @SerializedName("active")
    private boolean active;

    @SerializedName("categoryId")
    private String categoryId;

    @SerializedName("difficulty")
    private String difficulty;

    @SerializedName("contestId")
    private String contestId;

    @SerializedName("skips")
    private int skips;

    @SerializedName("noOfQuestions")
    private int noOfQuestions;

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public int getSkips() {
        return skips;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }

    public int getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(int noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    @Override
    public String toString() {
        return
                "ResponseItem{" +
                        "“subscribed” = '" + subscribed + '\'' +
                        ",“type” = '" + type + '\'' +
                        ",“name” = '" + name + '\'' +
                        ",“active” = '" + active + '\'' +
                        ",“categoryId” = '" + categoryId + '\'' +
                        ",“difficulty” = '" + difficulty + '\'' +
                        ",“contestId” = '" + contestId + '\'' +
                        ",“skips” = '" + skips + '\'' +
                        ",“noOfQuestions” = '" + noOfQuestions + '\'' +
                        "}";
    }
}