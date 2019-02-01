package com.example.quizapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.quizapp.R;
import com.example.quizapp.adapter.ContestLeaderBoardCardViewAdapter;
import com.example.quizapp.adapter.LeaderBoardCardViewAdapter;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.Response.CurrentLeaderBoardResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestLeaderboardActivity extends AppCompatActivity {

    private RecyclerView currentLeaderboardRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private IConnectAPI iConnectAPI;
    private String contestId;
    private List<CurrentLeaderBoardResponse> currentLeaderBoardResponses=new ArrayList<>();
    private ContestLeaderBoardCardViewAdapter contestLeaderBoardCardViewAdapter;
    ArrayList logos = new ArrayList<>(Arrays.asList(R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_leaderboard);
        contestId=getIntent().getStringExtra("contestId");
        currentLeaderboardRecyclerView = findViewById(R.id.current_leader_board_recycler_view);
        linearLayoutManager = new LinearLayoutManager(currentLeaderboardRecyclerView.getContext());
        currentLeaderboardRecyclerView.setLayoutManager(linearLayoutManager);
        iConnectAPI= AppController.retrofitLeaderboard.create(IConnectAPI.class);
        iConnectAPI.getContestLeaderboard(contestId).enqueue(new Callback<List<CurrentLeaderBoardResponse>>() {
            @Override
            public void onResponse(Call<List<CurrentLeaderBoardResponse>> call, Response<List<CurrentLeaderBoardResponse>> response) {
                if(response.isSuccessful()){
                    currentLeaderBoardResponses.addAll(response.body());
                }
                contestLeaderBoardCardViewAdapter = new ContestLeaderBoardCardViewAdapter(currentLeaderBoardResponses, logos);
                currentLeaderboardRecyclerView.setAdapter(contestLeaderBoardCardViewAdapter);
                contestLeaderBoardCardViewAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<List<CurrentLeaderBoardResponse>> call, Throwable t) {
                Log.e("Failure", t.getMessage());
            }
        });
    }
}
