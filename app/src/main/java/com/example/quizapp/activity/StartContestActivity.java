package com.example.quizapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.response.contestDetails.GetContestDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartContestActivity extends AppCompatActivity {

    private IConnectAPI iConnectAPI;
    TextView categoryName, noOfQuestions,difficultyLevel,noOfSkips,contestName;
    private GetContestDetailsResponse getContestDetailsResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_contest_layout);
        Intent intent = getIntent();
        String contestId=intent.getStringExtra("contestId");

        ImageView ContestimageView=(ImageView)findViewById(R.id.ContestImage);
        ContestimageView.setImageResource(R.drawable.ic_launcher_background) ;

        categoryName=findViewById(R.id.startContestCategoryName);
        noOfQuestions=findViewById(R.id.noOfQuestions);
        difficultyLevel=findViewById(R.id.difficultyLevel);
        noOfSkips=findViewById(R.id.questionsSkips);
        contestName=findViewById(R.id.NameOfContest);


        Button leaderButton=findViewById(R.id.LeaderBoardButton);
        final Button subscribeButton=findViewById(R.id.subscribe);


           leaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartContestActivity.this, "current leaderboard" ,Toast.LENGTH_SHORT ).show();
               // Intent intent= new Intent(StartContestActivity.this, LeaderBoard.class);
               // startActivity(intent);

            }
        });

        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Toast.makeText(StartContestActivity.this, "Subscribed" ,Toast.LENGTH_SHORT ).show();

            }
        });



      iConnectAPI= AppController.retrofit.create(IConnectAPI.class);
        iConnectAPI.getContestDetails(contestId).enqueue(new Callback<GetContestDetailsResponse>() {
            @Override
            public void onResponse(Call<GetContestDetailsResponse> call, Response<GetContestDetailsResponse> response) {
               if(response.isSuccessful())
               {
                   getContestDetailsResponse=response.body();
                   if(getContestDetailsResponse.getResponse().isSubscribed())
                   {

                       subscribeButton.setText("Start");
                   }
                   else
                   {
                       subscribeButton.setText("Subscribe");
                   }

                   categoryName.setText(getContestDetailsResponse.getResponse().getCategoryId());
                   noOfQuestions.setText(getContestDetailsResponse.getResponse().getNoOfQuestions());
                   difficultyLevel.setText(getContestDetailsResponse.getResponse().getDifficulty());
                   noOfSkips.setText(getContestDetailsResponse.getResponse().getSkips());
                   contestName.setText(getContestDetailsResponse.getResponse().getName());


               }
            }

            @Override
            public void onFailure(Call<GetContestDetailsResponse> call, Throwable t) {

                Toast.makeText(StartContestActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });




    }


}
