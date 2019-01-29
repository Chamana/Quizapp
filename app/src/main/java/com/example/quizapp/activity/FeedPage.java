package com.example.quizapp.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.quizapp.R;

public class FeedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_text_container);

        SearchView searchView = findViewById(R.id.searchBox);
        ImageButton profileButton= findViewById(R.id.profileButton);
        ImageButton notificationButton=findViewById(R.id.notificationButton);
        EditText writePost=findViewById(R.id.writePost);

    }
}
