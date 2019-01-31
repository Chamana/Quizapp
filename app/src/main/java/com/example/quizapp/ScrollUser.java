package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizapp.adapter.LikeDislikePost;
import com.example.quizapp.adapter.UserFeedAdapter;
import com.example.quizapp.api.CommentCommunicator;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.response.FollowResponseDTOList1Item;
import com.example.quizapp.models.response.FollowResponseFollowerListItem;
import com.example.quizapp.models.response.PostListItem;
import com.example.quizapp.models.response.UserFeedResponse;
import com.example.quizapp.models.response.UserProfileResponse;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ScrollUser extends AppCompatActivity implements CommentCommunicator, UserFeedAdapter.LikeCommunicator {


    private ArrayList<List<PostListItem>> PostListItems = new ArrayList<>();
    int likedBoolean;
    String likedPostId;
    String userId="1";


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    TextView followers,following,post,name,getFollowers,getFollowing;
    ImageView userdp;
    LinearLayout followers_ll,following_ll;
    List<FollowResponseFollowerListItem> followerList = new ArrayList<>();
    List<FollowResponseDTOList1Item> followingList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_user);
        post=findViewById(R.id.post1);
        userdp=findViewById(R.id.user_iv1);

        followers=findViewById(R.id.follower1);
        following=findViewById(R.id.following1);

        followers_ll=findViewById(R.id.followers_ll);
        following_ll=findViewById(R.id.following_ll);


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
        iApi1.getUserInfo(1).enqueue(new Callback<UserProfileResponse>() {
            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                name.setText(response.body().getUsername());
                Glide.with(userdp.getContext()).load(response.body().getUserImageURL()).into(userdp);
                followers.setText(String.valueOf(response.body().getFollowResponseFollowerList().size()));
                following.setText(String.valueOf(response.body().getFollowResponseDTOList1().size()));
                followerList.addAll(response.body().getFollowResponseFollowerList());
                followingList.addAll(response.body().getFollowResponseDTOList1());
                followers_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i= new Intent(ScrollUser.this,FollowerActivity.class);
                        if(followerList!=null) {
                            i.putParcelableArrayListExtra("followerList", new ArrayList(followerList));
                        }
                        startActivity(i);
                    }
                });
                following_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ScrollUser.this,FollowingActivity.class).putExtra("followingList", (Serializable) followingList));

                    }
                });



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
        iApi.getUserFeed(1).enqueue(new Callback<UserFeedResponse>() {
            @Override
            public void onResponse(Call<UserFeedResponse> call, Response<UserFeedResponse> response) {
                System.out.println(response.body());
                int postcount=response.body().getPostList().size();
//                for(int i=0;i<response.body().getPostList().size();i++)
//                {
//                    list<
//                }
//                response.body().getPostList().get(i).getPostLikes();

                post.setText(String.valueOf(postcount));
                mAdapter=new UserFeedAdapter(response.body().getPostList(),likedBoolean,ScrollUser.this,"1");
                ((UserFeedAdapter) mAdapter).setMethod(ScrollUser.this);

                mRecyclerView.setAdapter(mAdapter);
//                Toast.makeText(ScrollUser.this, ""+response.body(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClickOfButton(String postId) {
        Toast.makeText(this, "agaya bc"+postId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void returnLikeStatus(int likedBoolean, String postId) {
        this.likedBoolean=likedBoolean;
        this.likedPostId=postId;
        if(likedBoolean==1)
            likePostMethod();
        if(likedBoolean==0)
            dislikePostMethod();

    }

    public void likePostMethod() {
        System.out.println("LIKING");
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.7.137:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iConnectAPI = retrofit.create(IConnectAPI.class);
        iConnectAPI.postLike(likedPostId, userId).enqueue(new Callback<LikeDislikePost>() {
            @Override
            public void onResponse(Call<LikeDislikePost> call, Response<LikeDislikePost> response) {
                Log.d("TSTRESPONSE",response.body().toString());
            }

            @Override
            public void onFailure(Call<LikeDislikePost> call, Throwable t) {
                System.out.println("Couldn't Like");

            }
        });
    }

    public void dislikePostMethod(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.177.7.137:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        IConnectAPI iConnectAPI = retrofit.create(IConnectAPI.class);
        iConnectAPI.postDislike(likedPostId,userId).enqueue(new Callback<LikeDislikePost>() {
            @Override
            public void onResponse(Call<LikeDislikePost> call, Response<LikeDislikePost> response) {
                Log.d("TSTRESPONSE",response.body().toString());
                //System.out.println(response.body().getMessage());
            }
            @Override
            public void onFailure(Call<LikeDislikePost> call, Throwable t) {
            }
        });
    }
}
