package com.example.quizapp.models.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class FollowResponseFollowerListItem implements Parcelable {

    @SerializedName("userImageURL")
    private String userImageURL;

    @SerializedName("userId")
    private String userId;

    @SerializedName("username")
    private String username;

    public void setUserImageURL(String userImageURL) {
        this.userImageURL = userImageURL;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return
                "FollowResponseFollowerListItem{" +
                        "userImageURL = '" + userImageURL + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userImageURL);
        dest.writeString(this.userId);
        dest.writeString(this.username);
    }

    public FollowResponseFollowerListItem() {
    }

    protected FollowResponseFollowerListItem(Parcel in) {
        this.userImageURL = in.readString();
        this.userId = in.readString();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<FollowResponseFollowerListItem> CREATOR = new Parcelable.Creator<FollowResponseFollowerListItem>() {
        @Override
        public FollowResponseFollowerListItem createFromParcel(Parcel source) {
            return new FollowResponseFollowerListItem(source);
        }

        @Override
        public FollowResponseFollowerListItem[] newArray(int size) {
            return new FollowResponseFollowerListItem[size];
        }
    };
}