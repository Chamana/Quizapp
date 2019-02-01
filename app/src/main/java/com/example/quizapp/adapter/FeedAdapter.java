package com.example.quizapp.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.fragment.FeedPageFragment;
import com.example.quizapp.models.Response.ResponseItem;
import com.example.quizapp.response.FeedsListItem;


import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<FeedsListItem> feedList;
    List<ResponseItem> adsList;
    int likedBoolean;
    String userId;
    FeedPageCommunicator feedPageCommunicator;


    public FeedAdapter(List<FeedsListItem> homeFeedResponses, int likedBoolean, String userId, FeedPageFragment feedPageFragment) {
        this.feedList=homeFeedResponses;
        this.likedBoolean=likedBoolean;
        this.userId=userId;
        this.feedPageCommunicator=feedPageFragment;
    }

//    public FeedAdapter(List<FeedsListItem> feedList, int likedBoolean, String userId, FeedPageCommunicator feedPageCommunicator) {
//        this.feedList = feedList;
//        this.likedBoolean = likedBoolean;
//        this.userId = userId;
//        this.feedPageCommunicator=feedPageCommunicator;
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        if(i==0){
            View textView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_text, viewGroup, false);
            return new TextViewHolder(textView);
        }
        if(i==1){
            View imageView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_image_layout, viewGroup, false);
            return new ImageViewHolder(imageView);
        }
        else{
            View videoView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_video, viewGroup, false);
            return new VideoViewHolder(videoView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder.getItemViewType()==0){
            if(feedList.get(i).getPost().getPostLikes().contains(userId)){
                likedBoolean=1;
            }
            ((TextViewHolder) viewHolder).bind(feedList.get(i).getUsername(), feedList.get(i).getImage(), feedList.get(i).getPost().getDescription(), feedList.get(i).getPost().getPostLikes().size(), feedList.get(i).getPost().getPostsComments().size(),likedBoolean, feedList.get(i).getPost().getPostId());
        }
        else if(viewHolder.getItemViewType()==1){
            if(feedList.get(i).getPost().getPostLikes().contains(userId)){
                likedBoolean=1;
            }
            ((ImageViewHolder) viewHolder).bind(feedList.get(i).getUsername(), feedList.get(i).getImage(), feedList.get(i).getPost().getDescription(), feedList.get(i).getPost().getPostLikes().size(), feedList.get(i).getPost().getPostsComments().size(), feedList.get(i).getPost().getUrl(),likedBoolean, feedList.get(i).getPost().getPostId());
        }
        else{
            if(feedList.get(i).getPost().getPostLikes().contains(userId)){
                likedBoolean=1;
            }
            ((VideoViewHolder) viewHolder).bind(feedList.get(i).getUsername(), feedList.get(i).getImage(), feedList.get(i).getPost().getDescription(), feedList.get(i).getPost().getPostLikes().size(), feedList.get(i).getPost().getPostsComments().size(), feedList.get(i).getPost().getUrl(),likedBoolean, feedList.get(i).getPost().getPostId());
        }

    }

    @Override
    public int getItemViewType(int position) {
        String type=feedList.get(position).getPost().getType();
        if(type.equalsIgnoreCase("text")){
            System.out.println("returning Text");

            return 0;
        }
        else if(type.equalsIgnoreCase("image")){
            System.out.println("Returning Image");

            return 1;
        }
        else
            return 2;
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView posteeNameTextView;
        ImageView posteeImageButton;
        TextView postTextView;
        TextView numberDisplay;
        Button likedButton;
        int staticLikes;
        public TextViewHolder(@ NonNull View itemview) {
//            super(itemView);
            super(itemview);
            posteeNameTextView=itemview.findViewById(R.id.posteeNameT);
            posteeImageButton=itemview.findViewById(R.id.posteeProfilePictureT);
            postTextView=itemview.findViewById(R.id.postText);
            numberDisplay=itemview.findViewById(R.id.numberDisplayerT);
            likedButton=itemview.findViewById(R.id.likeButtonT);
        }
        public void bind(String posteeName, String posteeImgURL, String description, final int likes, final int comments, int liked, final String currentPostId){
            posteeNameTextView.setText(posteeName+"'s new Post!");
//            posteeImageButton.setBackgroundResource();
            postTextView.setText(description);
            numberDisplay.setText(likes+" Likes . "+comments+" Comments!");
            if(liked==1) {
                likedButton.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            staticLikes=likes;
            likedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(likedBoolean==0){
                        likedBoolean=1;
                        staticLikes=staticLikes+1;
                        numberDisplay.setText((staticLikes)+" Likes and "+comments+" Comments!");
                        likedButton.setBackgroundColor(Color.parseColor("#FF0000"));
                    }
                    else{
                        likedBoolean=0;
                        staticLikes=staticLikes-1;
                        numberDisplay.setText((staticLikes)+" Likes and "+comments+" Comments!");
                        likedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    feedPageCommunicator.returnLikeStatus(likedBoolean, currentPostId);
                }
            });
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView posteeNameTextView;
        ImageView posteeImageButton;
        TextView numberDisplay;
        ImageView postImage;
        TextView description;
        Button likedButton;
        int staticLikes;
        public ImageViewHolder(@NonNull View itemview) {
            super(itemview);
            posteeNameTextView=itemview.findViewById(R.id.posteeTextI);
            posteeImageButton=itemview.findViewById(R.id.posteepictureI);
            numberDisplay=itemview.findViewById(R.id.textView2);
            postImage=itemview.findViewById(R.id.postImageI);
            description=itemview.findViewById(R.id.descriptonI);
            likedButton=itemview.findViewById(R.id.likedButtonI);
        }
        public void bind(String posteeName, String posteeImgURL, String description, final int likes, final int comments, String postImgURL, int liked, final String currentPostId){
            posteeNameTextView.setText(posteeName+" uploaded a new Image!");
            Glide.with(posteeImageButton.getContext()).load(posteeImgURL).into(posteeImageButton);
            numberDisplay.setText(likes+" Likes and "+comments+" Comments!");
            Glide.with(postImage.getContext()).load(postImgURL).into(postImage);
            this.description.setText(description);
            if(liked==1) {
                likedButton.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            staticLikes=likes;
            likedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(likedBoolean==0){
                        likedBoolean=1;
                        staticLikes+=1;
                        numberDisplay.setText((staticLikes)+" Likes and "+comments+" Comments!");
                        likedButton.setBackgroundColor(Color.parseColor("#FF0000"));
                    }
                    else{
                        likedBoolean=0;
                        staticLikes-=1;
                        numberDisplay.setText((staticLikes)+" Likes and "+comments+" Comments!");
                        likedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    feedPageCommunicator.returnLikeStatus(likedBoolean, currentPostId);
                }
            });
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView posteeNameTextView;
        ImageView posteeImageButton;
        TextView numberDisplay;
        VideoView postVideo;
        TextView description;
        Button likedButton;
        int staticLikes;

        public VideoViewHolder(@NonNull View itemview) {
            super(itemview);
            posteeNameTextView=itemview.findViewById(R.id.posteeNameV);
            posteeImageButton=itemview.findViewById(R.id.posteeprofilePictureV);
            numberDisplay=itemview.findViewById(R.id.numberDisplayerV);
            description=itemview.findViewById(R.id.descriptionV);
            postVideo=itemview.findViewById(R.id.videoView);
            likedButton=itemview.findViewById(R.id.likeButtonV);
            description=itemview.findViewById(R.id.descriptionV);

        }
        public void bind(String posteeName, String posteeImgURL, String description, final int likes, final int comments, String postVideoURL, final int liked, final String currentPostId){
            posteeNameTextView.setText(posteeName+" uploaded a new Video!");
            Glide.with(posteeImageButton.getContext()).load(posteeImgURL).into(posteeImageButton);
            Glide.with(posteeImageButton.getContext()).load(posteeImgURL).into(posteeImageButton);
            numberDisplay.setText(likes+" Likes and "+comments+" Comments!");
            this.description.setText(description);
            postVideo.setVideoURI(Uri.parse(postVideoURL));
            postVideo.requestFocus();
            postVideo.start();
            staticLikes=likes;

            if(liked==1) {
                likedButton.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            likedButton.setOnClickListener(new View.OnClickListener() {
                //staticLikes2=likes;
                @Override
                public void onClick(View v) {
                    if(likedBoolean==0){
                        likedBoolean=1;
                        staticLikes+=1;
                        numberDisplay.setText((staticLikes)+" Likes and "+comments+" Comments!");
                        likedButton.setBackgroundColor(Color.parseColor("#FF0000"));
                    }
                    else{
                        likedBoolean=0;
                        staticLikes-=1;
                        numberDisplay.setText((staticLikes)+" Likes and "+comments+" Comments!");
                        likedButton.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                    feedPageCommunicator.returnLikeStatus(likedBoolean, currentPostId);
                }
            });

        }
    }

    public interface FeedPageCommunicator {
        void returnLikeStatus(int likedBoolean, String postId);
    }
}

