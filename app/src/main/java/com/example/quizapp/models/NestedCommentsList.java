package com.example.quizapp.models;

import android.os.Parcel;
import android.os.Parcelable;


public class NestedCommentsList implements Parcelable {

    private String nestedCommentId;
    private String userimage;
    private String reply;
    private String userId;
    private String username;

    public NestedCommentsList(String nestedCommentId, String userimage, String reply, String userId, String username) {
        this.nestedCommentId = nestedCommentId;
        this.userimage = userimage;
        this.reply = reply;
        this.userId = userId;
        this.username = username;
    }

    public String getNestedCommentId() {
        return nestedCommentId;
    }

    public void setNestedCommentId(String nestedCommentId) {
        this.nestedCommentId = nestedCommentId;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public NestedCommentsList createFromParcel(Parcel in) {
            return new NestedCommentsList(in);
        }

        public NestedCommentsList[] newArray(int size) {
            return new NestedCommentsList[size];
        }
    };

    @Override
    public String toString() {
        return "NestedCommentsList{" +
                "nestedCommentId='" + nestedCommentId + '\'' +
                ", userimage='" + userimage + '\'' +
                ", reply='" + reply + '\'' +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public NestedCommentsList(Parcel parcel){
        nestedCommentId=parcel.readString();
        userimage=parcel.readString();
        reply=parcel.readString();
        userId=parcel.readString();
        username=parcel.readString();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nestedCommentId);
        dest.writeString(userimage);
        dest.writeString(reply);
        dest.writeString(userId);
        dest.writeString(username);
    }


}
