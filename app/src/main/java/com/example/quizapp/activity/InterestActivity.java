package com.example.quizapp.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.InterestAdapter;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.request.LoginRequest;
import com.example.quizapp.models.request.UpdateProfileRequest;
import com.example.quizapp.models.response.InterestListResponse;
import com.example.quizapp.models.response.UpdateProfileResponse;
import com.example.quizapp.utils.AppController;
import com.example.quizapp.utils.ProgressBarUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class InterestActivity extends AppCompatActivity implements InterestAdapter.ICommunicator, View.OnClickListener {

    private ImageView back_iv;
    private RecyclerView interest_rv;
    private TextView next_tv;
    private int count;
    private List<String> interestList;
    private List<String> selectedInterestList;
    private InterestAdapter adapter;
    private int backCount=0;
    private ProgressBarUtility progressBarUtility;
    private int index=0,size=10;
    private IConnectAPI iConnectAPI;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        init();
    }

    private void init() {
        progressBarUtility=new ProgressBarUtility(this);
        iConnectAPI=AppController.interest.create(IConnectAPI.class);
        progressBar=findViewById(R.id.progress_pb);
        back_iv=findViewById(R.id.back_iv);
        interest_rv=findViewById(R.id.interest_rv);
        next_tv=findViewById(R.id.next_tv);
        back_iv.setOnClickListener(this);
        next_tv.setOnClickListener(this);
        interestList=new ArrayList<>();
        selectedInterestList=new ArrayList<>();
        getCategory();
        interest_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1)){
                    progressBar.setVisibility(View.VISIBLE);
                    Call<InterestListResponse> interestListResponseCall=iConnectAPI.getInterestList(index,size);
                    interestListResponseCall.enqueue(new Callback<InterestListResponse>() {
                        @Override
                        public void onResponse(Call<InterestListResponse> call, Response<InterestListResponse> response) {
                            if(response.body().getInterests().size()>0){
                                interestList.addAll(response.body().getInterests());
                                index++;
                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(InterestActivity.this, "No more interest available.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<InterestListResponse> call, Throwable t) {
                            Toast.makeText(InterestActivity.this, "Unable to connect to server.Try again after sometime.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void getCategory() {
        Call<InterestListResponse> interestListResponseCall=iConnectAPI.getInterestList(index,size);
        interestListResponseCall.enqueue(new Callback<InterestListResponse>() {
            @Override
            public void onResponse(Call<InterestListResponse> call, Response<InterestListResponse> response) {
                if(response.body().getInterests().size()>0){
                    interestList.addAll(response.body().getInterests());
                    interest_rv.setHasFixedSize(true);
                    interest_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter= new InterestAdapter(InterestActivity.this,interestList);
                    index++;
                    interest_rv.setAdapter(adapter);

                }else{
                    Toast.makeText(InterestActivity.this, "Unable to fetch the interest list.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InterestListResponse> call, Throwable t) {
                Toast.makeText(InterestActivity.this, "Unable to connect to server.Try again after sometime.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void addInterest(String selectedInterest) {
        selectedInterestList.add(selectedInterest);
    }

    @Override
    public void removeInterest(String selectedInterest) {
        selectedInterestList.remove(selectedInterest);
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:  backCount++;
                                if(backCount==1){
                                    Toast.makeText(this, "Press again to exit the app.", Toast.LENGTH_SHORT).show();
                                }else{
                                    backCount=0;
                                    finishAffinity();
                                }
                                break;
            case R.id.next_tv:  if(selectedInterestList.size()>4){
                                    String interests=selectedInterestList.toString().replace("[","").replace("]","");
                                    saveInterest(interests);
                                }else{
                                    Toast.makeText(this, "Please select atleast 5 interest.", Toast.LENGTH_SHORT).show();
                                }
                                break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private void saveInterest(String interests) {
        IConnectAPI iConnectAPI=AppController.user_profile_retrofit.create(IConnectAPI.class);
        UpdateProfileRequest updateProfileRequest=new UpdateProfileRequest(AppController.sharedPreferences.getString("userId",""),null,0,null,interests,null);
        Call<UpdateProfileResponse> updateProfileResponseCall=iConnectAPI.addInterestList(AppController.sharedPreferences.getString("userId",""),updateProfileRequest);
        progressBarUtility.displayProgress("Storing interests please wait.");
        updateProfileResponseCall.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if(response.body().isStatus()){
                    progressBarUtility.cancelDialog();
                    SharedPreferences.Editor editor=AppController.sharedPreferences.edit();
                    editor.putBoolean("isInterestSet",true);
                    editor.commit();
                    finish();
                    Toast.makeText(InterestActivity.this, "Interest saved sucessfully.", Toast.LENGTH_SHORT).show();
                }else{
                    progressBarUtility.cancelDialog();
                    Toast.makeText(InterestActivity.this, "Error while storing interest.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(InterestActivity.this, "Unable to reach the server.Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
