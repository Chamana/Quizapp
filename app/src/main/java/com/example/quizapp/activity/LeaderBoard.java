package com.example.quizapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.adapter.LeaderBoardCardViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class LeaderBoard extends AppCompatActivity {

    ArrayList logos = new ArrayList<>(Arrays.asList(R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon));
    ArrayList<String> nameList = new ArrayList(Arrays.asList("10", "11", "12", "13", "14", "15", "DD", "Divanshu", "Anshu", "Srivastava"));
    private RecyclerView leaderBoardRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        leaderBoardRecyclerView = findViewById(R.id.leader_board_recycler_view);
        linearLayoutManager = new LinearLayoutManager(leaderBoardRecyclerView.getContext());
        leaderBoardRecyclerView.setLayoutManager(linearLayoutManager);
        LeaderBoardCardViewAdapter leaderBoardCardViewAdapter = new LeaderBoardCardViewAdapter(nameList, logos);
        leaderBoardRecyclerView.setAdapter(leaderBoardCardViewAdapter);
    }
}
