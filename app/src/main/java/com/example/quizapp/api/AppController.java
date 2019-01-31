package com.example.quizapp.api;

import android.app.Application;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    public static Retrofit retrofit = null;
    public static Retrofit retrofitContest = null;
    public static Retrofit retrofitLeaderboard = null;

    @Override
    public void onCreate() {
        super.onCreate();
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.7.110:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        if (null == retrofitContest) {

            OkHttpClient client = new OkHttpClient.Builder().build();

            retrofitContest = new Retrofit.Builder()
                    .baseUrl("http://10.177.7.110:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }

        if(null==retrofitLeaderboard){
            OkHttpClient client = new OkHttpClient.Builder().build();

            retrofitLeaderboard = new Retrofit.Builder()
                    .baseUrl("http://10.177.7.118:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

        }
    }
}
