package com.example.quizapp.models.request;

public class UpdateProfileRequest {
    String userId;
    String userImageURL;
    long dateOfBirth;
    String username;
    String interest;
    String about;

    public UpdateProfileRequest(String userId, String userImageURL, long dateOfBirth, String username, String interest, String about) {
        this.userId = userId;
        this.userImageURL = userImageURL;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.interest = interest;
        this.about = about;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "UpdateProfileRequest{" +
                "userId='" + userId + '\'' +
                ", userImageURL='" + userImageURL + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", username='" + username + '\'' +
                ", interest='" + interest + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
