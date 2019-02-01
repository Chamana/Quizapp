package com.example.quizapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.CommentAdapter;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.Response.AddCommentResponse;
import com.example.quizapp.models.Response.CommentResponse;
import com.example.quizapp.models.Response.PostsCommentsItem;
import com.example.quizapp.utils.ProgressBarUtility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_iv;
    private RecyclerView comments_rv;
    private EditText writeComment_et;
    private ProgressBarUtility progressBarUtility;
    private String postId;
    private CommentAdapter commentAdapter;
    private List<PostsCommentsItem> postsCommentsItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        init();
        setInit();
    }

    public void init(){
        back_iv=findViewById(R.id.back_iv);
        comments_rv=findViewById(R.id.comments_rv);
        writeComment_et=findViewById(R.id.writeComment_et);
        progressBarUtility=new ProgressBarUtility(this);
        postId="5c519f85caad620568b723e3";
    }

    private void setInit() {
        back_iv.setOnClickListener(this);
        writeComment_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_GO){
                    postComment(writeComment_et.getText().toString());
                }
                return false;
            }
        });
        getComments();
    }

    private void postComment(String toString) {
        IConnectAPI iConnectAPI= AppController.posts_retrofit.create(IConnectAPI.class);
//        Call<AddCommentResponse> addCommentResponseCall=iConnectAPI.postComments(postId,AppController.sharedPreferences.getString("userId",""),AppController.sharedPreferences.getString("name",""),writeComment_et.getText().toString(),"https://cdn4.vectorstock.com/i/1000x1000/84/68/hipster-man-in-glasses-avatar-profile-userpic-on-vector-8988468.jpg");
        Call<AddCommentResponse> addCommentResponseCall=iConnectAPI.postComments(postId,"09b7a071-2f61-4c99-981a-d852b534f018","Maharishi",writeComment_et.getText().toString(),"https://cdn4.vectorstock.com/i/1000x1000/84/68/hipster-man-in-glasses-avatar-profile-userpic-on-vector-8988468.jpg");
        progressBarUtility.displayProgress("Replying comment..");
        addCommentResponseCall.enqueue(new Callback<AddCommentResponse>() {
            @Override
            public void onResponse(Call<AddCommentResponse> call, Response<AddCommentResponse> response) {
                if(null!=response.body() && response.body().isStatus()){
                    progressBarUtility.cancelDialog();
                    writeComment_et.setText("");
                    Toast.makeText(CommentActivity.this, "Commented successfully.", Toast.LENGTH_SHORT).show();
                    getComments();
                }else{
                    progressBarUtility.cancelDialog();
                    Toast.makeText(CommentActivity.this, "Failed to comment.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCommentResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(CommentActivity.this, "Unable to reach the server.Please try again after sometime.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getComments() {
        IConnectAPI iConnectAPI= AppController.posts_retrofit.create(IConnectAPI.class);
        Call<CommentResponse> getCommentCall=iConnectAPI.getComments(postId);
        progressBarUtility.displayProgress("Loading comments...");
        getCommentCall.enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if(response.body()!=null){
                    progressBarUtility.cancelDialog();
                    comments_rv.setHasFixedSize(true);
                    comments_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    postsCommentsItemList=response.body().getPostsComments();
                    commentAdapter=new CommentAdapter(postsCommentsItemList,postId);
                    comments_rv.setAdapter(commentAdapter);
                }else{
                    progressBarUtility.cancelDialog();
                    comments_rv.setVisibility(View.GONE);
                    Toast.makeText(CommentActivity.this, "Error occured.Please try again after sometime.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(CommentActivity.this, "Unable to reach the server.Please try again after sometime.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
