package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.models.response.FollowResponseDTOList1Item;
import com.example.quizapp.models.response.FollowResponseFollowerListItem;

import java.util.ArrayList;
import java.util.List;

public class FollowerActivity extends AppCompatActivity {
    List<FollowResponseFollowerListItem> followerList ;
    TextView tvfollow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);
        tvfollow=findViewById(R.id.tvfollow);
        System.out.println("PRINT"+ getIntent().getSerializableExtra("followerList"));
        followerList= (List<FollowResponseFollowerListItem>) getIntent().getSerializableExtra("followerList");
        for (int i=0;i<followerList.size();i++){
            Toast.makeText(this, ""+followerList.get(i).getUsername(), Toast.LENGTH_SHORT).show();
            tvfollow.setText(tvfollow.getText().toString() +"\n "+ followerList.get(i).getUsername());
        }
        Toast.makeText(this, ""+getIntent().getSerializableExtra("followerList"), Toast.LENGTH_SHORT).show();
    }
}
