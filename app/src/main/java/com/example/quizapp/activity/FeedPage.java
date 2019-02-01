//package com.example.quizapp.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//
//import com.example.quizapp.R;
//import com.example.quizapp.adapter.FeedAdapter;
//import com.example.quizapp.api.IConnectAPI;
//import com.example.quizapp.response.FeedsListItem;
//import com.example.quizapp.response.LikeDislikePost;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class FeedPage extends AppCompatActivity implements FeedAdapter.FeedPageCommunicator {
//
//    int likedBoolean;
//    String likedPostId;
//    String userId;
//    FeedAdapter feedAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_feed_page);
//
////        String userId=sharedPreferences.getString("userId","1");
//        userId="1";
//        final RecyclerView recyclerView=findViewById(R.id.feedRV);
//        final List<FeedsListItem> feedList=new ArrayList<>();
//
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.177.7.137:8080")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        IConnectAPI iConnectAPI=retrofit.create(IConnectAPI.class);
//        iConnectAPI.getHomeFeedResponse(userId).enqueue(new Callback<com.example.quizapp.response.HomeFeedResponse>() {
//            @Override
//            public void onResponse(Call<com.example.quizapp.response.HomeFeedResponse> call, Response<com.example.quizapp.response.HomeFeedResponse> response) {
//                System.out.println("Inside response method");
//                System.out.println(response.body());
//                feedAdapter = new FeedAdapter(response.body().getFeedsList(),likedBoolean, userId, FeedPage.this);
//                recyclerView.setLayoutManager( new LinearLayoutManager(FeedPage.this));
//                recyclerView.setAdapter(feedAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<com.example.quizapp.response.HomeFeedResponse> call, Throwable t) {
//                System.out.println("fail"+t.getLocalizedMessage());
//
//            }
//        });
//
////        profileButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent=new Intent(FeedPage.this, User.class);
////                startActivity(intent);
////            }
////        });
//
////        notificationButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent=new Intent(FeedPage.this, NotificationList.class);
////                startActivity(intent);
////            }
////        });
//    }
//
//    @Override
//    public void returnLikeStatus(int likedBoolean, String postId) {
//        this.likedBoolean = likedBoolean;
//        this.likedPostId = postId;
//        if (likedBoolean == 1)
//            likePostMethod(postId);
//        if (likedBoolean==0)
//            dislikePostMethod(postId);
//    }
//
//    public void likePostMethod(String postId) {
//        System.out.println("LIKING");
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.177.7.137:8080")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        IConnectAPI iConnectAPI = retrofit.create(IConnectAPI.class);
//        if (likedBoolean == 0) {
//            iConnectAPI.postLike(likedPostId, userId).enqueue(new Callback<LikeDislikePost>() {
//                @Override
//                public void onResponse(Call<LikeDislikePost> call, Response<LikeDislikePost> response) {
//                    Log.d("TSTRESPONSE",response.body().toString());
//                }
//
//                @Override
//                public void onFailure(Call<LikeDislikePost> call, Throwable t) {
//                    System.out.println("Couldn't Like");
//
//                }
//            });
//        }
//    }
//
//    public void dislikePostMethod(String postId){
//        System.out.println("DISLIKING");
//        OkHttpClient client = new OkHttpClient.Builder().build();
//        final Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.177.7.137:8080")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//        IConnectAPI iConnectAPI = retrofit.create(IConnectAPI.class);
//            System.out.println("Dislikng post");
//            iConnectAPI.postDislike(likedPostId,userId).enqueue(new Callback<LikeDislikePost>() {
//                @Override
//                public void onResponse(Call<LikeDislikePost> call, Response<LikeDislikePost> response) {
//                    Log.d("TSTRESPONSE",response.body().toString());
//                    //System.out.println(response.body().getMessage());
//                }
//                @Override
//                public void onFailure(Call<LikeDislikePost> call, Throwable t) {
//                }
//            });
//    }
//}
