package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class StartContestActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_contest_layout);

        ImageView ContestimageView=(ImageView)findViewById(R.id.ContestImage);
        ContestimageView.setImageResource(R.drawable.ic_launcher_background) ;

        Button startButton =findViewById(R.id.StartGame);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartContestActivity.this , "button clicked ",Toast.LENGTH_LONG).show();
            }
        });
    }


}
