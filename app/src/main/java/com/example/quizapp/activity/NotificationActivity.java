package com.example.quizapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.NotificationAdapter;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.fragment.FeedPageFragment;
import com.example.quizapp.response.FeedsListItem;
import com.example.quizapp.response.NotificationResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationActivity extends AppCompatActivity {
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        //        String userId=sharedPreferences.getString("userId","1");
        userId="8a8a7a69-b642-4f7f-a4ef-2b43a370c3fe";
        final RecyclerView recyclerView=findViewById(R.id.notificationRV);
        final List<FeedsListItem> notificationList=new ArrayList<>();

        OkHttpClient client = new OkHttpClient.Builder().build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.7.147:8085")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iConnectAPI=retrofit.create(IConnectAPI.class);
        iConnectAPI.getNotifications(userId,0).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                System.out.println("WWW"+response.body());
                if(response.body().isStatus()==true) {
                    NotificationAdapter notificationAdapter = new NotificationAdapter(response.body().getNotificationHistoryDTOList());
                    recyclerView.setLayoutManager( new LinearLayoutManager(NotificationActivity.this));
                    recyclerView.setAdapter(notificationAdapter);
                }
                else{
                    Button errorButton=findViewById(R.id.errorbutton);
                    errorButton.setVisibility(View.VISIBLE);
                    errorButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity((new Intent(NotificationActivity.this, MainActivity.class)));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText(NotificationActivity.this, "OOPS"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
