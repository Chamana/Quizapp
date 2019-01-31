package com.example.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.quizapp.R;
import com.example.quizapp.adapter.FeedAdapter;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.response.FeedsListItem;
import com.example.quizapp.response.Post;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.quizapp.utils.AppController.sharedPreferences;

public class FeedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_page);

//        String userId=sharedPreferences.getString("userId","1");
        String userId="1";
        SearchView searchView = findViewById(R.id.searchBox);
        final ImageButton profileButton= findViewById(R.id.profileButton);
        final ImageButton notificationButton=findViewById(R.id.notificationButton);
        final EditText writePost=findViewById(R.id.writePost);
        final RecyclerView recyclerView=findViewById(R.id.feedRV);
        final List<FeedsListItem> feedList=new ArrayList<>();
        final List<Post> postList=new ArrayList<>();

        OkHttpClient client = new OkHttpClient.Builder().build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.7.137:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iConnectAPI=retrofit.create(IConnectAPI.class);
        iConnectAPI.getHomeFeedResponse(userId).enqueue(new Callback<com.example.quizapp.response.HomeFeedResponse>() {
            @Override
            public void onResponse(Call<com.example.quizapp.response.HomeFeedResponse> call, Response<com.example.quizapp.response.HomeFeedResponse> response) {
                System.out.println("Inside response method");
                System.out.println(response.body());
                FeedAdapter feedAdapter=new FeedAdapter(response.body().getFeedsList());
                recyclerView.setLayoutManager( new LinearLayoutManager(FeedPage.this));
                recyclerView.setAdapter(feedAdapter);
            }

            @Override
            public void onFailure(Call<com.example.quizapp.response.HomeFeedResponse> call, Throwable t) {
                System.out.println("fail"+t.getLocalizedMessage());

            }
        });

//        profileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(FeedPage.this, User.class);
//                startActivity(intent);
//            }
//        });

//        notificationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(FeedPage.this, NotificationList.class);
//                startActivity(intent);
//            }
//        });


    }
}
