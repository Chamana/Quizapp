package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Holder.ContestCardViewHolder;
import com.example.quizapp.Icommunicator;
import com.example.quizapp.R;
import com.example.quizapp.models.response.ResponseItem;

import java.util.ArrayList;
import java.util.List;

public class ContestCardViewAdapter extends RecyclerView.Adapter {

    private ArrayList<String> images;
    private List<ResponseItem> contestNameList = new ArrayList<>();
    private String contestId;
    Icommunicator icommunicator;
    public ContestCardViewAdapter(List<ResponseItem> contestNameList,ArrayList images, Icommunicator icommunicator){
        this.images=images;
        this.contestNameList=contestNameList;
        this.icommunicator = icommunicator;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen_fragment_contest_card_view, viewGroup, false);
        return new ContestCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.e("Contest Id from adapter",contestNameList.get(i).getContestId().toString());
        ((ContestCardViewHolder) viewHolder).bind(contestNameList.get(i).getName(),contestNameList.get(i).getCategoryId(), contestNameList.get(i).getContestId(), images.get(i),icommunicator);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
