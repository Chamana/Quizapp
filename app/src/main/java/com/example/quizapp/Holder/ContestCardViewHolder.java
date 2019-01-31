package com.example.quizapp.Holder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizapp.Icommunicator;
import com.example.quizapp.R;
import com.example.quizapp.activity.MainActivity;
import com.example.quizapp.activity.StartContestActivity;


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
    public void bind(String contestName, String categoryName, final String contestId, Object contestImageUrl, final Icommunicator icommunicator){
        this.contestName.setText(contestName);
        this.categoryName.setText(categoryName);
        this.contestId=contestId;
        Log.e("Contest Id",contestId);
        Glide.with(this.contestImage.getContext()).load(contestImageUrl).into(this.contestImage);
        cardViewContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(cardViewContest.getContext(),contestId.toString(),Toast.LENGTH_LONG).show();
                icommunicator.navigate(getAdapterPosition());




            }
        });
    }
}
