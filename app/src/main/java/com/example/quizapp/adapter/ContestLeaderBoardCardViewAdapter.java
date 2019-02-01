package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Holder.ContestLeaderBoardCardViewHolder;
import com.example.quizapp.R;
import com.example.quizapp.models.Response.CurrentLeaderBoardResponse;
import com.example.quizapp.models.Response.OverallLeaderboardResponse;

import java.util.ArrayList;
import java.util.List;

public class ContestLeaderBoardCardViewAdapter extends RecyclerView.Adapter {
    private ArrayList<String> images;
    private List<CurrentLeaderBoardResponse> nameList;
    private int[] rank;


    public ContestLeaderBoardCardViewAdapter(List<CurrentLeaderBoardResponse> nameList, ArrayList images) {
        this.images = images;
        this.nameList = nameList;
        rank = new int[nameList.size()];
        Log.e("namelist", nameList.toString());
        for (int i = 1; i <= nameList.size(); i++)
            rank[i - 1] = i;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.current_leaderboard_card_layout, viewGroup, false);
        return new ContestLeaderBoardCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ContestLeaderBoardCardViewHolder) viewHolder).bind(nameList.get(i).getUserName(), String.valueOf(rank[i]), String.valueOf(nameList.get(i).getScore()), images.get(i));

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
