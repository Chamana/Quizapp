package com.example.quizapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.models.Response.PostsCommentsItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.view.View.GONE;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<PostsCommentsItem> postsCommentsItems;
    Set<Integer> postitionList = new HashSet<Integer>();
    String postId;

    public CommentAdapter(List<PostsCommentsItem> postsComments,String postId) {
        this.postsCommentsItems=postsComments;
        this.postId=postId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_comment,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Glide.with(viewHolder.pp_iv.getContext()).load(postsCommentsItems.get(i).getUserimage()).into(viewHolder.pp_iv);
        viewHolder.name_tv.setText(postsCommentsItems.get(i).getUsername());
        viewHolder.comment_tv.setText(postsCommentsItems.get(i).getDescription());
        if(postsCommentsItems.get(i).getNestedPostComments().size()>0){
            postitionList.add(i);
            viewHolder.nestCommSize_tv.setVisibility(View.VISIBLE);
            viewHolder.nestCommSize_tv.setText("View "+postsCommentsItems.get(i).getNestedPostComments().size()+" comments");
            viewHolder.nestedComment_rv.setLayoutManager(new LinearLayoutManager(viewHolder.nestedComment_rv.getContext()));
            viewHolder.nestedComment_rv.setAdapter(new NestedCommentAdapter(postsCommentsItems.get(i).getNestedPostComments(),postsCommentsItems.get(i).getCommentId(),postId));
            viewHolder.nestedComment_rv.setVisibility(GONE);
        }else{
            viewHolder.nestCommSize_tv.setVisibility(GONE);
            viewHolder.nestedComment_rv.setVisibility(GONE);
        }
        viewHolder.nestCommSize_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.nestCommSize_tv.getTag().equals("vis")){
                    viewHolder.nestCommSize_tv.setTag("notvis");
                    viewHolder.nestedComment_rv.setVisibility(GONE);
                }else{
                    viewHolder.nestCommSize_tv.setTag("vis");
                    viewHolder.nestedComment_rv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postsCommentsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pp_iv;
        TextView name_tv,comment_tv,nestCommSize_tv;
        RecyclerView nestedComment_rv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pp_iv=itemView.findViewById(R.id.pp_iv);
            name_tv=itemView.findViewById(R.id.name_tv);
            comment_tv=itemView.findViewById(R.id.comment_tv);
            nestCommSize_tv=itemView.findViewById(R.id.nestCommSize_tv);
            nestedComment_rv=itemView.findViewById(R.id.nestedComment_rv);
        }
    }
}
