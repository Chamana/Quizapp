package com.example.quizapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.activity.NestedCommentActivity;
import com.example.quizapp.models.NestedCommentsList;
import com.example.quizapp.models.Response.NestedPostCommentsItem;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class NestedCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<NestedPostCommentsItem> nestedPostCommentsItems;
    String commentId,postId;
    private static final int TYPE_COMMENT = 1;
    private static final int TYPE_FOOTER = 2;

    public NestedCommentAdapter(List<NestedPostCommentsItem> nestedPostComments,String commentId,String postId) {
        this.nestedPostCommentsItems = nestedPostComments;
        this.commentId=commentId;
        this.postId=postId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_COMMENT) {
            return new CommentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_nested_comment, viewGroup, false));
        } else {
            return new FooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nested_comment_footer, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CommentViewHolder) {
            CommentViewHolder commentViewHolder=(CommentViewHolder)viewHolder;
            Glide.with(commentViewHolder.pp_iv.getContext()).load(nestedPostCommentsItems.get(i).getUserimage()).into(commentViewHolder.pp_iv);
            commentViewHolder.name_tv.setText(nestedPostCommentsItems.get(i).getUsername());
            commentViewHolder.comment_tv.setText(nestedPostCommentsItems.get(i).getReply());
        }else if (viewHolder instanceof FooterViewHolder){
            final FooterViewHolder footerViewHolder=(FooterViewHolder)viewHolder;
            footerViewHolder.reply_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(footerViewHolder.reply_tv.getContext(), ""+nestedPostCommentsItems.size(), Toast.LENGTH_SHORT).show();
                    Context context=footerViewHolder.reply_tv.getContext();
//
                    ArrayList<NestedCommentsList> nestedCommentsList=new ArrayList<>();
                    for(int j=0;j<nestedPostCommentsItems.size();j++) {
                        NestedCommentsList nestedCommentsList1 = new NestedCommentsList(nestedPostCommentsItems.get(j).getNestedCommentId(), nestedPostCommentsItems.get(j).getUserimage(), nestedPostCommentsItems.get(j).getReply(), nestedPostCommentsItems.get(j).getUserId(), nestedPostCommentsItems.get(j).getUsername());
                        nestedCommentsList.add(nestedCommentsList1);
                    }
                    Toast.makeText(context, ""+nestedCommentsList.size(), Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,NestedCommentActivity.class).putExtra("postId",postId).putExtra("commentId",commentId).putParcelableArrayListExtra("nestedCommentList",(ArrayList<? extends Parcelable>) nestedCommentsList));
//                    context.startActivity(new Intent(context,NestedCommentActivity.class));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return nestedPostCommentsItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == nestedPostCommentsItems.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_COMMENT;
        }
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        CircularImageView pp_iv;
        TextView name_tv, comment_tv;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            pp_iv = itemView.findViewById(R.id.pp_iv);
            name_tv = itemView.findViewById(R.id.name_tv);
            comment_tv = itemView.findViewById(R.id.comment_tv);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        TextView reply_tv;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            reply_tv=itemView.findViewById(R.id.reply_tv);
        }
    }
}
