package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.response.UserProfileResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        OkHttpClient client = new OkHttpClient.Builder().build();
        //http://10.0.2.2:8000 or http://localhost:8000
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iApi = retrofit.create(IConnectAPI.class);
        iApi.getUserInfo(1).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {

            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {

            }
        });
    }


}
