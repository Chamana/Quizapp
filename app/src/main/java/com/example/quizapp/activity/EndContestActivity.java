package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.request.SharePostRequest;
import com.example.quizapp.models.response.SharePostResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndContestActivity extends AppCompatActivity {


    private IConnectAPI iConnectAPI;
    private SharePostRequest sharePostRequest=new SharePostRequest();

    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_contest_layout);


        ImageView ContestimageView = (ImageView) findViewById(R.id.ContestImage);
        ContestimageView.setImageResource(R.drawable.ic_launcher_background);

        Button buttonHome = findViewById(R.id.home);
        Button buttonLeader = findViewById(R.id.leader);
        shareButton=findViewById(R.id.shareButton);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EndContestActivity.this, "home", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(EndContestActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        buttonLeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EndContestActivity.this, "leader", Toast.LENGTH_LONG).show();
                 Intent intent= new Intent(EndContestActivity.this, LeaderBoard.class);
                 startActivity(intent);
            }
        });
        sharePostRequest.setPostId("5c516ee4caad6203c5fa5e12");
        sharePostRequest.setDestinationId("8a8a7a69-b642-4f7f-a4ef-2b43a370c3fe");

        iConnectAPI=AppController.postRetrofit.create(IConnectAPI.class);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iConnectAPI.getPostResponse(sharePostRequest).enqueue(new Callback<SharePostResponse>() {
                    @Override
                    public void onResponse(Call<SharePostResponse> call, Response<SharePostResponse> response) {
                        if(response.body().isStatus()) {

                            Toast.makeText(EndContestActivity.this, "Post Shared Successfully!", Toast.LENGTH_LONG).show();

                        } else{

                            Toast.makeText(EndContestActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<SharePostResponse> call, Throwable t) {
                        Log.e("Failure",t.getMessage());

                        Toast.makeText(EndContestActivity.this, "Connection error", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }

}
