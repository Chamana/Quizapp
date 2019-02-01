package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.models.NestedCommentsList;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class ReplyCommentAdapter extends RecyclerView.Adapter<ReplyCommentAdapter.ViewHolder> {

    List<NestedCommentsList> nestedCommentsLists;
    public ReplyCommentAdapter(List<NestedCommentsList> nestedCommentsListList) {
        this.nestedCommentsLists=nestedCommentsListList;
    }

    @NonNull
    @Override
    public ReplyCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_reply_comment,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyCommentAdapter.ViewHolder viewHolder, int i) {
        Glide.with(viewHolder.pp_iv.getContext()).load(nestedCommentsLists.get(i).getUserimage()).into(viewHolder.pp_iv);
        viewHolder.name_tv.setText(nestedCommentsLists.get(i).getUsername());
        viewHolder.comment_tv.setText(nestedCommentsLists.get(i).getReply());
    }

    @Override
    public int getItemCount() {
        return nestedCommentsLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView pp_iv;
        TextView name_tv,comment_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pp_iv=itemView.findViewById(R.id.pp_iv);
            name_tv=itemView.findViewById(R.id.name_tv);
            comment_tv=itemView.findViewById(R.id.comment_tv);
        }
    }
}
