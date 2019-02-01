package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.ContestPage;
import com.example.quizapp.R;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.request.Contest;
import com.example.quizapp.models.request.SubscribeButtonRequest;
import com.example.quizapp.models.request.UserGetAllContestRequest;
import com.example.quizapp.models.Response.contestDetails.GetContestDetailsResponse;
import com.example.quizapp.utils.DynamicContestPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartContestActivity extends AppCompatActivity {

    TextView categoryName, noOfQuestions, difficultyLevel, noOfSkips, contestName;
    private IConnectAPI iConnectAPI;
    private GetContestDetailsResponse getContestDetailsResponse;
    private UserGetAllContestRequest userGetAllContestRequest = new UserGetAllContestRequest();
    private SubscribeButtonRequest subscribeButtonRequest = new SubscribeButtonRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_contest_layout);
        Intent intent = getIntent();
        final String contestId = intent.getStringExtra("contestId");

        userGetAllContestRequest.setUserId("u1");

        ImageView ContestimageView = (ImageView) findViewById(R.id.ContestImage);
        ContestimageView.setImageResource(R.drawable.ic_launcher_background);

        categoryName = findViewById(R.id.startContestCategoryName);
        noOfQuestions = findViewById(R.id.noOfQuestions);
        difficultyLevel = findViewById(R.id.difficultyLevel);
        noOfSkips = findViewById(R.id.questionsSkips);
        contestName = findViewById(R.id.NameOfContest);


        Button leaderButton = findViewById(R.id.LeaderBoardButton);
        final Button subscribeButton = findViewById(R.id.subscribe);


        leaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartContestActivity.this, LeaderBoard.class);
                startActivity(intent);

            }
        });

        iConnectAPI = AppController.retrofit.create(IConnectAPI.class);
        iConnectAPI.getContestDetails(contestId, userGetAllContestRequest).enqueue(new Callback<GetContestDetailsResponse>() {
            @Override
            public void onResponse(Call<GetContestDetailsResponse> call, Response<GetContestDetailsResponse> response) {
                if (response.isSuccessful()) {
                    getContestDetailsResponse = response.body();
                    if (getContestDetailsResponse.getResponse().isSubscribed()) {

                        subscribeButton.setText("START");
                        subscribeButton.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(StartContestActivity.this, R.drawable.startcontest), null, null, null);
                        subscribeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(StartContestActivity.this, ContestPage.class));

                            }
                        });
                    } else {
                        subscribeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                IConnectAPI iConnectAPI2 = AppController.retrofit.create(IConnectAPI.class);
                                iConnectAPI2.getIfSubscribed(contestId, userGetAllContestRequest).enqueue(new Callback<SubscribeButtonRequest>() {
                                    @Override
                                    public void onResponse(Call<SubscribeButtonRequest> call, Response<SubscribeButtonRequest> response) {
                                        if (response.isSuccessful()) {
                                            subscribeButtonRequest = response.body();
                                            if (subscribeButtonRequest.getStatus().equals("success")) {
                                                subscribeButton.setText("START");
                                                subscribeButton.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(StartContestActivity.this, R.drawable.startcontest), null, null, null);
                                                subscribeButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {

                                                        Intent intent=new Intent(StartContestActivity.this, ContestPage.class);
                                                        intent.putExtra("contestId",contestId);
                                                        startActivity(intent);

                                                    }
                                                });
                                            } else {
                                                Toast.makeText(StartContestActivity.this, subscribeButtonRequest.getErrorMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<SubscribeButtonRequest> call, Throwable t) {

                                        Log.e("Failure", "Mera code nai chal rah");
                                    }
                                });

                            }
                        });

                      /* subscribeButton.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {



                           }});*/

                    }

                    categoryName.setText(getContestDetailsResponse.getResponse().getCategoryId());
                    noOfQuestions.setText(String.valueOf(getContestDetailsResponse.getResponse().getNoOfQuestions()));
                    difficultyLevel.setText(getContestDetailsResponse.getResponse().getDifficulty());
                    noOfSkips.setText(String.valueOf(getContestDetailsResponse.getResponse().getSkips()));
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
