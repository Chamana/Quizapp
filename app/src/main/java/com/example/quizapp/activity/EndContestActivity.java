package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class EndContestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_contest_layout);


        ImageView ContestimageView=(ImageView)findViewById(R.id.ContestImage);
        ContestimageView.setImageResource(R.drawable.ic_launcher_background) ;

        Button buttonHome =findViewById(R.id.home);
        Button buttonLeader =findViewById(R.id.leader);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EndContestActivity.this,"home",Toast.LENGTH_SHORT).show();
               // Intent intent=new Intent(EndContestActivity.this,HomeActivity.class);
                //startActivity(intent);
            }
        });

        buttonLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EndContestActivity.this,"leader",Toast.LENGTH_LONG).show();
                // Intent intent= new Intent(EndContestActivity.this, LeaderBoard.class);
                // startActivity(intent);
            }
        });

    }
}
