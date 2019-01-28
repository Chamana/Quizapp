package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Holder.ContestCardViewHolder;
import com.example.quizapp.R;

import java.util.ArrayList;

public class ContestCardViewAdapter extends RecyclerView.Adapter {

    private ArrayList<String> images,nameList;
    public ContestCardViewAdapter(ArrayList nameList,ArrayList images){
        this.images=images;
        this.nameList=nameList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_screen_fragment_contest_card_view, viewGroup, false);
        return new ContestCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ContestCardViewHolder) viewHolder).bind(nameList.get(i),  images.get(i));

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
}
