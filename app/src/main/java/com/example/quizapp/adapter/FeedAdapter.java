package com.example.quizapp.adapter;

import android.app.ProgressDialog;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.activity.Video_check;
import com.example.quizapp.response.FeedsListItem;
import com.example.quizapp.response.Post;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<FeedsListItem> feedList;
    List<Post> postList;

    public FeedAdapter(List<FeedsListItem> feedList) {
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        if(i==0){
            View textView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_text, viewGroup, false);
            return new TextViewHolder(textView);
        }
        if(i==1){
            System.out.println("In onCreateVH-image");
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
            ((TextViewHolder) viewHolder).bind(feedList.get(i).getUsername(), feedList.get(i).getImage(), feedList.get(i).getPost().getDescription(), feedList.get(i).getPost().getPostLikes().size(), feedList.get(i).getPost().getPostsComments().size());
        }
        else if(viewHolder.getItemViewType()==1){
            ((ImageViewHolder) viewHolder).bind(feedList.get(i).getUsername(), feedList.get(i).getImage(), feedList.get(i).getPost().getDescription(), feedList.get(i).getPost().getPostLikes().size(), feedList.get(i).getPost().getPostsComments().size(), feedList.get(i).getPost().getUrl());
        }
        else{
            ((VideoViewHolder) viewHolder).bind(feedList.get(i).getUsername(), feedList.get(i).getImage(), feedList.get(i).getPost().getDescription(), feedList.get(i).getPost().getPostLikes().size(), feedList.get(i).getPost().getPostsComments().size(), feedList.get(i).getPost().getUrl());

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
        public TextViewHolder(@ NonNull View itemview) {
//            super(itemView);
            super(itemview);
            posteeNameTextView=itemview.findViewById(R.id.posteeNameT);
            posteeImageButton=itemview.findViewById(R.id.posteeProfilePictureT);
            postTextView=itemview.findViewById(R.id.postText);
            numberDisplay=itemview.findViewById(R.id.numberDisplayerT);
        }
        public void bind(String posteeName, String posteeImgURL, String description, int likes, int comments){
            posteeNameTextView.setText(posteeName+"'s new Post!");
//            posteeImageButton.setBackgroundResource();
            postTextView.setText(description);
            numberDisplay.setText(likes+" Likes . "+comments+" Comments!");
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView posteeNameTextView;
        ImageView posteeImageButton;
        TextView numberDisplay;
        ImageView postImage;
        TextView description;
        public ImageViewHolder(@NonNull View itemview) {
            super(itemview);
            posteeNameTextView=itemview.findViewById(R.id.posteeTextI);
            posteeImageButton=itemview.findViewById(R.id.posteepictureI);
            numberDisplay=itemview.findViewById(R.id.textView2);
            postImage=itemview.findViewById(R.id.postImageI);
            description=itemview.findViewById(R.id.descriptonI);
        }
        public void bind(String posteeName, String posteeImgURL, String description, int likes, int comments, String postImgURL){
            posteeNameTextView.setText(posteeName+" uploaded a new Image!");
            Glide.with(posteeImageButton.getContext()).load(posteeImgURL).into(posteeImageButton);
            numberDisplay.setText(likes+" Likes and "+comments+" Comments!");
            Glide.with(postImage.getContext()).load(postImgURL).into(postImage);
            this.description.setText(description);
        }
    }

    class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView posteeNameTextView;
        ImageView posteeImageButton;
        TextView numberDisplay;
        VideoView postVideo;
        TextView description;

        public VideoViewHolder(@NonNull View itemview) {
            super(itemview);
            posteeNameTextView=itemview.findViewById(R.id.posteeNameV);
            posteeImageButton=itemview.findViewById(R.id.posteeprofilePictureV);
            numberDisplay=itemview.findViewById(R.id.numberDisplayerV);
            description=itemview.findViewById(R.id.descriptionV);
            postVideo=itemview.findViewById(R.id.videoView);

        }
        public void bind(String posteeName, String posteeImgURL, String description, int likes, int comments, String postVideoURL){
            posteeNameTextView.setText(posteeName+" uploaded a new Video!");
            Glide.with(posteeImageButton.getContext()).load(posteeImgURL).into(posteeImageButton);
            Glide.with(posteeImageButton.getContext()).load(posteeImgURL).into(posteeImageButton);
            numberDisplay.setText(likes+" Likes and "+comments+" Comments!");
            postVideo.setVideoURI(Uri.parse(postVideoURL));
            postVideo.requestFocus();
            postVideo.start();

        }
    }
}
