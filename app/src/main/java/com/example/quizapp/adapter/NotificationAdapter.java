package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.response.NotificationHistoryDTOListItem;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<NotificationHistoryDTOListItem> notificationList=new ArrayList<>();

    public NotificationAdapter(List<NotificationHistoryDTOListItem> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_container, viewGroup, false);
        return new NotificationAdapter.NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((NotificationViewHolder)viewHolder).bind(notificationList.get(i).getMessage());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }


    private class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        public NotificationViewHolder(View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.notificationContent);
        }
        void bind(String message){
            this.message.setText(message);
        }
    }
}
