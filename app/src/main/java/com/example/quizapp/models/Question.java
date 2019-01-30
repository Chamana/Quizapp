package com.example.quizapp.models;

import java.util.List;

public class Question {

    private String errorMsg;
    private String ansType;
    private long duration;
    private List<String> optionContent;
    private List<String> optionId;
    private String quesCat;
    private String quesContent;
    private String quesDifficulty;
    private String quesId;
    private String quesName;
    private String quesType;
    private String status;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAnsType() {
        return ansType;
    }

    public void setAnsType(String ansType) {
        this.ansType = ansType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public List<String> getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(List<String> optionContent) {
        this.optionContent = optionContent;
    }

    public List<String> getOptionId() {
        return optionId;
    }

    public void setOptionId(List<String> optionId) {
        this.optionId = optionId;
    }

    public String getQuesCat() {
        return quesCat;
    }

    public void setQuesCat(String quesCat) {
        this.quesCat = quesCat;
    }

    public String getQuesContent() {
        return quesContent;
    }

    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    public String getQuesDifficulty() {
        return quesDifficulty;
    }

    public void setQuesDifficulty(String quesDifficulty) {
        this.quesDifficulty = quesDifficulty;
    }

    public String getQuesId() {
        return quesId;
    }

    public void setQuesId(String quesId) {
        this.quesId = quesId;
    }

    public String getQuesName() {
        return quesName;
    }

    public void setQuesName(String quesName) {
        this.quesName = quesName;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
