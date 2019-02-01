package com.example.quizapp.utils;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    public static Retrofit auth_retrofit=null;
    public static Retrofit posts_retrofit=null;
    public static Retrofit user_profile_retrofit=null;
    public static Retrofit static_contest_retrofit=null;
    public static Retrofit dynamic_contest_retrofit=null;
    public static Retrofit notification_retrofit=null;
    public static Retrofit interest=null;

    public OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

//        getApplicationContext().startService(new Intent(getApplicationContext(), TransferService.class));
        if(null == auth_retrofit){
            auth_retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.7.82:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        if(null == posts_retrofit){
            posts_retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.7.137:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        if(null == user_profile_retrofit){
            user_profile_retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.177.7.124:8081")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        if(null == interest){
            interest=new Retrofit.Builder()
                    .baseUrl("http://10.177.7.131:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        if(null==static_contest_retrofit){
            static_contest_retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.177.7.120:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        if(null==dynamic_contest_retrofit){
            dynamic_contest_retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.177.7.115:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        if(null==notification_retrofit){
            notification_retrofit=new Retrofit.Builder()
                    .baseUrl("http://10.177.7.144:8085")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        sharedPreferences=getSharedPreferences("com.example.quizapp",MODE_PRIVATE);
    }
}
