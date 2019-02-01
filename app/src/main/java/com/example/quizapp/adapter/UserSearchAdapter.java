package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.models.Response.UserListItem;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.ViewHolder> {
    List<UserListItem> userListItems;
    public UserSearchAdapter(List<UserListItem> userListItems) {
        this.userListItems=userListItems;
    }

    @NonNull
    @Override
    public UserSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_search_user,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final UserSearchAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(viewHolder.user_iv.getContext()).load(userListItems.get(i).getUserImageURL()).into(viewHolder.user_iv);
        viewHolder.name_tv.setText(userListItems.get(i).getUsername());
        viewHolder.user_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.user_iv.getContext(), ""+userListItems.get(i).getUserId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout user_ll;
        CircularImageView user_iv;
        TextView name_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_ll=itemView.findViewById(R.id.user_ll);
            user_iv=itemView.findViewById(R.id.user_iv);
            name_tv=itemView.findViewById(R.id.name_tv);
        }
    }
}
