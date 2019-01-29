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
    private ImageView userImage;
    public LeaderBoardCardViewHolder(@NonNull View itemView) {
        super(itemView);
        userName=itemView.findViewById(R.id.leaderboardUserName);
        userImage=itemView.findViewById(R.id.leaderboardCircularImage);
    }
    public void bind(String userName, Object userImageUrl){
        this.userName.setText(userName);
        Glide.with(this.userImage.getContext()).load(userImageUrl).into(this.userImage);
    }
}