package com.example.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.response.UserProfileResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class User extends AppCompatActivity {
    ImageView userdp;
    TextView followers, following, interest, about, name, dob;
    String userId = "c2b67514-cc58-42d1-904a-3b6083ecd6de";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userdp = findViewById(R.id.user_iv);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        interest = findViewById(R.id.interest);
        about = findViewById(R.id.about);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.DOB);

        OkHttpClient client = new OkHttpClient.Builder().build();
        //http://10.0.2.2:8000 or http://localhost:8000
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.7.124:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iApi = retrofit.create(IConnectAPI.class);
        iApi.getUserInfo(userId).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                name.setText(response.body().getUsername());
                Glide.with(userdp.getContext()).load(response.body().getUserImageURL()).into(userdp);
                followers.setText(String.valueOf(response.body().getFollowResponseFollowerList().size()));
                following.setText(String.valueOf(response.body().getFollowResponseDTOList1().size()));
                interest.setText(response.body().getInterest());
                about.setText(response.body().getAbout());
                Date dateTime = new java.util.Date(response.body().getDateOfBirth());
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String date = dateFormat.format(dateTime);
                dob.setText(date);


            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                System.out.println("error" + t.getLocalizedMessage());
            }
        });
    }


}
