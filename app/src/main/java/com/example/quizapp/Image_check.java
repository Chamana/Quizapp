package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Image_check extends AppCompatActivity {
    ImageView image_iv;
    String imageUrl="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSgFjqauUmzscdX8SXD_sMqQvBIJ5sKYseA_DMwwG0_0LyGPX2k";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_image_layout);
        image_iv=findViewById(R.id.PostImage);
        Glide.with(image_iv.getContext()).load(imageUrl).into(image_iv);
    }
}
