package com.example.quizapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.quizapp.R;
import com.example.quizapp.adapter.ContestCardViewAdapter;
import com.example.quizapp.adapter.LeaderBoardCardViewAdapter;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.fragment.ContestFragment;
import com.example.quizapp.models.response.GetAllContestResponse;
import com.example.quizapp.models.response.OverallLeaderboardResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoard extends AppCompatActivity {

    ArrayList logos = new ArrayList<>(Arrays.asList(R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon));
    private RecyclerView leaderBoardRecyclerView;
    private IConnectAPI iConnectAPI;
    private List<OverallLeaderboardResponse> overallLeaderboardResponse=new ArrayList();
    private LinearLayoutManager linearLayoutManager;
    private LeaderBoardCardViewAdapter leaderBoardCardViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        leaderBoardRecyclerView = findViewById(R.id.leader_board_recycler_view);
        linearLayoutManager = new LinearLayoutManager(leaderBoardRecyclerView.getContext());
        leaderBoardRecyclerView.setLayoutManager(linearLayoutManager);
        iConnectAPI = AppController.retrofitLeaderboard.create(IConnectAPI.class);
        iConnectAPI.getLeaderboard().enqueue(new Callback<List<OverallLeaderboardResponse>>() {
            @Override
            public void onResponse(Call<List<OverallLeaderboardResponse>> call, Response<List<OverallLeaderboardResponse>> response) {
                if(response.isSuccessful()){
                    overallLeaderboardResponse.addAll(response.body());
                }
                leaderBoardCardViewAdapter = new LeaderBoardCardViewAdapter(overallLeaderboardResponse, logos);
                leaderBoardRecyclerView.setAdapter(leaderBoardCardViewAdapter);
                leaderBoardCardViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<OverallLeaderboardResponse>> call, Throwable t) {
                Log.e("Failure", t.getMessage());
            }
        });


    }
}
