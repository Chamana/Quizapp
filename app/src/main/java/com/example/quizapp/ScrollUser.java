package com.example.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizapp.adapter.UserFeedAdapter;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.response.PostListItem;
import com.example.quizapp.models.response.UserFeedResponse;
import com.example.quizapp.models.response.UserProfileResponse;

import java.text.SimpleDateFormat;
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
    TextView followers,following,post,name;
    ImageView userdp;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_user);
        post=findViewById(R.id.post1);
        userdp=findViewById(R.id.user_iv1);
        followers=findViewById(R.id.follower1);
        following=findViewById(R.id.following1);
        name=findViewById(R.id.name1);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        OkHttpClient client1 = new OkHttpClient.Builder().build();
        //http://10.0.2.2:8000 or http://localhost:8000
        final Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://10.177.7.124:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client1)
                .build();
        IConnectAPI iApi1 = retrofit1.create(IConnectAPI.class);
        iApi1.getUserInfo(2).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                name.setText(response.body().getUsername());
                Glide.with(userdp.getContext()).load(response.body().getUserImageURL()).into(userdp);
                followers.setText(String.valueOf(response.body().getFollowResponseFollowerList().size()));
                following.setText(String.valueOf(response.body().getFollowResponseDTOList1().size()));




            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                System.out.println("error"+t.getLocalizedMessage());
            }
        });




        OkHttpClient client = new OkHttpClient.Builder().build();

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
                int postcount=response.body().getPostList().size();
                post.setText(String.valueOf(postcount));
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
