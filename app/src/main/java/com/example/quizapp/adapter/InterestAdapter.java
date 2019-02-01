package com.example.quizapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.activity.InterestActivity;

import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder> {
    ICommunicator iCommunicator;
    List<String> interestList;
    Set<Integer> postitionList = new HashSet<Integer>();

    public InterestAdapter(ICommunicator iCommunicator, List<String> interestList) {
        this.iCommunicator=iCommunicator;
        this.interestList=interestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_interest,viewGroup,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (postitionList.contains(i)) {
            selColor(viewHolder.interest_tv);
        } else {
            unselColor(viewHolder.interest_tv);
        }
        viewHolder.interest_tv.setText(interestList.get(i).toString());
        viewHolder.interest_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (postitionList.contains(i)) {
                    postitionList.remove(i);
                    iCommunicator.removeInterest(interestList.get(i));
                    unselColor(viewHolder.interest_tv);
                } else {
                    postitionList.add(i);
                    iCommunicator.addInterest(interestList.get(i));
                    selColor(viewHolder.interest_tv);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return interestList.size();
    }

    public void selColor(TextView interest_tv){
        interest_tv.setBackgroundDrawable(interest_tv.getResources().getDrawable(R.drawable.interest_bgk_selected));
        interest_tv.setTextColor(interest_tv.getResources().getColor(R.color.colorPrimary));
    }

    public void unselColor(TextView interest_tv){
        interest_tv.setBackgroundDrawable(interest_tv.getResources().getDrawable(R.drawable.interest_bgk_unselected));
        interest_tv.setTextColor(interest_tv.getResources().getColor(R.color.colorAccent));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView interest_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            interest_tv=itemView.findViewById(R.id.interest_tv);
        }
    }

    public interface ICommunicator{
        void addInterest(String selectedInterest);
        void removeInterest(String selectedInterest);
    }
}
