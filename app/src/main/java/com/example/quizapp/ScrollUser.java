package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.quizapp.adapter.UserFeedAdapter;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.response.PostListItem;
import com.example.quizapp.models.response.UserFeedResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScrollUser extends AppCompatActivity {


    private ArrayList<List<PostListItem>> PostListItems = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_user);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OkHttpClient client = new OkHttpClient.Builder().build();
        //http://10.0.2.2:8000 or http://localhost:8000
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.7.137:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iApi = retrofit.create(IConnectAPI.class);
        iApi.getUserFeed(2).enqueue(new Callback<UserFeedResponse>() {
            @Override
            public void onResponse(Call<UserFeedResponse> call, Response<UserFeedResponse> response) {
                System.out.println(response.body());
                mAdapter=new UserFeedAdapter(response.body().getPostList());
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(ScrollUser.this, ""+response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserFeedResponse> call, Throwable t) {
                Toast.makeText(ScrollUser.this, ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        iApi.getUserFeed(5).enqueue(new Callback<List<UserFeedResponse>>() {
//            @Override
//            public void onResponse(Call<UserFeedResponse> call, Response<List<UserFeedResponse>> response) {
//                for (int i = 0; i< response.body().size(); i++) {
//
//                    PostListItems.add(response.body().get(i).getPostList());
//                }
//                mAdapter = new UserFeedAdapter( PostListItems);
//                mRecyclerView.setAdapter(mAdapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<List<UserFeedResponse>> call, Throwable t) {
//
//            }
//        });





    }
}
