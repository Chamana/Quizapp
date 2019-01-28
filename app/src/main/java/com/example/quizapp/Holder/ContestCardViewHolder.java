package com.example.quizapp.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;

public class ContestCardViewHolder extends RecyclerView.ViewHolder {
    private TextView contestName;
    private ImageView contestImage;
    public ContestCardViewHolder(@NonNull View itemView) {
        super(itemView);
        contestName=itemView.findViewById(R.id.main_screen_fragment_contest_card_view_contest_name);
        contestImage=itemView.findViewById(R.id.main_screen_fragment_contest_card_view_image);
    }
    public void bind(String contestName, Object contestImageUrl){
        this.contestName.setText(contestName);
        Glide.with(this.contestImage.getContext()).load(contestImageUrl).into(this.contestImage);
    }
}
