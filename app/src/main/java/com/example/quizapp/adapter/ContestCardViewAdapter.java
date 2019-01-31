package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Holder.ContestCardViewHolder;
import com.example.quizapp.R;
import com.example.quizapp.models.response.ResponseItem;

import java.util.ArrayList;
import java.util.List;

public class ContestCardViewAdapter extends RecyclerView.Adapter {

    private ArrayList<String> images;
    private List<ResponseItem> contestNameList;
    private String contestId;
    public ContestCardViewAdapter(List<ResponseItem> contestNameList,ArrayList images){
        this.images=images;
        this.contestNameList=contestNameList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen_fragment_contest_card_view, viewGroup, false);
        return new ContestCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ContestCardViewHolder) viewHolder).bind(contestNameList.get(i).getName(),contestNameList.get(i).getCategoryId(), contestNameList.get(i).getContestId(), images.get(i));

    }

    @Override
    public int getItemCount() {
        return contestNameList.size();
    }
}
