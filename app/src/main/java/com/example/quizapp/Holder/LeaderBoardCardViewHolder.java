package com.example.quizapp.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;

public class LeaderBoardCardViewHolder extends RecyclerView.ViewHolder {
    private TextView userName;
    private TextView userRank;
    private TextView userScore;
    private ImageView userImage;
    public LeaderBoardCardViewHolder(@NonNull View itemView) {
        super(itemView);
        userName=itemView.findViewById(R.id.leaderboardUserName);
        userRank=itemView.findViewById(R.id.leaderboardRank);
        userScore=itemView.findViewById(R.id.leaderboardScore);
        userImage=itemView.findViewById(R.id.leaderboardCircularImage);
    }
    public void bind(String userName,String userRank,String userScore, Object userImageUrl){
        this.userName.setText(userName);
        this.userRank.setText(userRank);
        this.userScore.setText(userScore);
        Glide.with(this.userImage.getContext()).load(userImageUrl).into(this.userImage);
    }
}