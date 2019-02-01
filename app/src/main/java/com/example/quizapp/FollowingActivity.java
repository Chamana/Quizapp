package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.models.response.FollowResponseDTOList1Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FollowingActivity extends AppCompatActivity {
    List<FollowResponseDTOList1Item> followingList;
    TextView tvFollower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        tvFollower=findViewById(R.id.tvFollower);
        System.out.println("PRINT"+getIntent().getSerializableExtra("followingList"));
       followingList= (List<FollowResponseDTOList1Item>) getIntent().getSerializableExtra("followingList");

       for (int i=0;i<followingList.size();i++){
           Toast.makeText(this, ""+followingList.get(i).getUsername(), Toast.LENGTH_SHORT).show();
           tvFollower.setText(tvFollower.getText().toString() +"\n "+ followingList.get(i).getUsername());
       }
////       for (FollowResponseDTOList1Item follower:followingList) {
////
////            tvFollower.setText(tvFollower.getText().toString() +"\n "+ follower.getUsername());
////        }
    }
}
