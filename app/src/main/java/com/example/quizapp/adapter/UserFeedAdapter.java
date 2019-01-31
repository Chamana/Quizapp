package com.example.quizapp.adapter;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.Video_check;
import com.example.quizapp.api.CommentCommunicator;
import com.example.quizapp.models.response.PostListItem;

import java.util.ArrayList;
import java.util.List;

public class UserFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<PostListItem> postList;
    private ProgressDialog pd;
    private CommentCommunicator commentCommunicator;
    public void setMethod(CommentCommunicator commentCommunicator){
        this.commentCommunicator=commentCommunicator;
    }





    public UserFeedAdapter(List<PostListItem> postList) {
        this.postList=postList;
    }


    @Override
    public int getItemViewType(int position) {
        String type = postList.get(position).getType();
        if(type.equalsIgnoreCase("image"))
            return 1;
        else if(type.equalsIgnoreCase("video"))
            return 2;
        else
            return 0;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
//        if (postList.get(i).getType().equalsIgnoreCase("video")){
//            System.out.println("video type");
//            return new VideoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_text, viewGroup, false));
//
//
//        } else if (postList.get(i).getType().equalsIgnoreCase("image")) {
//            System.out.println("Image type");
//            return new ImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_image_layout, viewGroup, false));
//
//        }
//        else
//        {   System.out.println("Text type");
//            return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_text, viewGroup, false));
//
//        }
        if(viewType==1){
            return new ImageViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_image_layout, viewGroup, false));
        }else if(viewType==2){
            return new VideoViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_video, viewGroup, false));
        }else{
            return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_text, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {


        if (viewHolder.getItemViewType() == 1)

        {  ((ImageViewHolder) viewHolder).bind(postList.get(position));

        }
        else if (viewHolder.getItemViewType() == 2 )
        {
            ((VideoViewHolder) viewHolder).bind(postList.get(position));


        }
        else {
            ((TextViewHolder) viewHolder).bind(postList.get(position));

        }



    }

    @Override
    public int getItemCount() {
        return postList.size();
    }



    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView userDp;
        TextView username;
        TextView like,comment;



        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.PostText);
            userDp=itemView.findViewById(R.id.userdp1);
            username=itemView.findViewById(R.id.username1);
            like=itemView.findViewById(R.id.LikeText1);
            comment=itemView.findViewById(R.id.comment1);


        }
        public void bind(final PostListItem postListItem){
            this.textView.setText(String.valueOf("Post Text "+postListItem.getDescription()));
           this.userDp.setImageResource(R.drawable.bunny);
            this.username.setText("USERNAME " + postListItem.getUserId());
            like.setText(String.valueOf("Like " +postListItem.getPostLikes().size()));
            comment.setText(String.valueOf("Comments "+postListItem.getPostsComments().size()));
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentCommunicator.onClickOfButton(postListItem.getPostId());


                }
            });





     } }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageView userDp;
        TextView username;
        TextView like,comment;



        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.PostImage);
            userDp=itemView.findViewById(R.id.userdp2);
            username=itemView.findViewById(R.id.username2);
            like=itemView.findViewById(R.id.LikeText2);
            comment=itemView.findViewById(R.id.comment2);

        }
        public void bind(final PostListItem postListItem){

            Glide.with(imageView.getContext()).load(postListItem.getUrl()).into(imageView);
            this.username.setText("USERNAME " + postListItem.getUserId());
            this.userDp.setImageResource(R.drawable.bunny);
            like.setText(String.valueOf("Likes "+ postListItem.getPostLikes().size()));
            comment.setText(String.valueOf("Comments "+postListItem.getPostsComments().size()));
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentCommunicator.onClickOfButton(postListItem.getPostId());


                }
            });





        } }
    class VideoViewHolder extends RecyclerView.ViewHolder {
       VideoView videoView;
        ImageView userDp;
        TextView username;
        TextView like,comment;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            userDp=itemView.findViewById(R.id.userdp3);
            username=itemView.findViewById(R.id.username3);
            like=itemView.findViewById(R.id.LikeText3);
            comment=itemView.findViewById(R.id.comment3);
        }
        public void bind(final PostListItem postListItem){

            this.userDp.setImageResource(R.drawable.bunny);
            this.username.setText("USERNAME " + postListItem.getUserId());
            like.setText(String.valueOf("Likes "+postListItem.getPostLikes().size()));
            comment.setText(String.valueOf("Comments "+postListItem.getPostsComments().size()));
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentCommunicator.onClickOfButton(postListItem.getPostId());

                }
            });

            pd = new ProgressDialog(userDp.getContext());
            pd.setMessage("Buffering video please wait...");
            pd.show();

            Uri uri = Uri.parse(postListItem.getUrl());
            videoView.setVideoURI(uri);
            videoView.requestFocus();

            videoView.start();
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //close the progress dialog when buffering is done
                    pd.dismiss();
                }
            });

        }

     }
    }

