package com.example.quizapp.Holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.activity.MainActivity;

public class ContestCardViewHolder extends RecyclerView.ViewHolder {
    private TextView contestName;
    private TextView categoryName;
    private ImageView contestImage;
    private CardView cardViewContest;
    private String contestId;
    public ContestCardViewHolder(@NonNull View itemView) {
        super(itemView);
        contestName=itemView.findViewById(R.id.main_screen_fragment_contest_card_view_contest_name);
        categoryName=itemView.findViewById(R.id.main_screen_fragment_contest_card_view_category_name);
        contestImage=itemView.findViewById(R.id.main_screen_fragment_contest_card_view_image);
        cardViewContest = itemView.findViewById(R.id.contest_card_view);
    }
    public void bind(String contestName, String categoryName, final String contestId, Object contestImageUrl){
        this.contestName.setText(contestName);
        this.categoryName.setText(categoryName);
        this.contestId=contestId;
        Glide.with(this.contestImage.getContext()).load(contestImageUrl).into(this.contestImage);
        cardViewContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent=new Intent(cardViewContest.getContext(),StartContestActivity.class);
                intent.putExtra("contestId",contestId);*/
            }
        });
    }
}
