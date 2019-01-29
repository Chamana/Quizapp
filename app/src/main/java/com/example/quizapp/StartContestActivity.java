package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StartContestActivity extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_contest_layout);

        ImageView ContestimageView=(ImageView)findViewById(R.id.ContestImage);
        ContestimageView.setImageResource(R.drawable.ic_launcher_background) ;

        Button startButton =findViewById(R.id.StartGame);
        Button leaderButton=findViewById(R.id.LeaderBoardButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartContestActivity.this , "start game clicked ",Toast.LENGTH_LONG).show();
               // Intent intent = new Intent(StartContestActivity.this, ContestPage.class);
               // startActivity(intent);
            }
        });


        leaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartContestActivity.this, "current leaderboard" ,Toast.LENGTH_SHORT ).show();
               // Intent intent= new Intent(StartContestActivity.this, LeaderBoard.class);
               // startActivity(intent);

            }
        });
    }


}
