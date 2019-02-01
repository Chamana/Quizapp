package com.example.quizapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.ReplyCommentAdapter;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.NestedCommentsList;
import com.example.quizapp.api.AppController;
import com.example.quizapp.models.Response.AddCommentResponse;
import com.example.quizapp.utils.ProgressBarUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NestedCommentActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_iv;
    private RecyclerView nestedComment_rv;
    private EditText writeReply_et;
    private String postId,commentId;
    private ProgressBarUtility progressBarUtility;
    private List<NestedCommentsList> nestedCommentsListList;
    private ReplyCommentAdapter replyCommentAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_comment);
        init();
        setInit();

    }
    public void init(){
        postId=getIntent().getStringExtra("postId");
        commentId=getIntent().getStringExtra("commentId");
        nestedCommentsListList=getIntent().getParcelableArrayListExtra("nestedCommentList");
        back_iv=findViewById(R.id.back_iv);
        nestedComment_rv=findViewById(R.id.nestedComment_rv);
        writeReply_et=findViewById(R.id.writeReply_et);
        progressBarUtility=new ProgressBarUtility(this);
        nestedComment_rv=findViewById(R.id.nestedComment_rv);
        nestedComment_rv.setHasFixedSize(true);
        nestedComment_rv.setLayoutManager(new LinearLayoutManager(this));
        replyCommentAdapter=new ReplyCommentAdapter(nestedCommentsListList);
        nestedComment_rv.setAdapter(replyCommentAdapter);

    }
    public void setInit(){
        back_iv.setOnClickListener(this);
        writeReply_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_GO){
                    postReply(writeReply_et.getText().toString());
                }
                return false;
            }
        });
    }

    private void postReply(String toString) {
        IConnectAPI iConnectAPI=AppController.posts_retrofit.create(IConnectAPI.class);
//        final Call<AddCommentResponse> replyCall=iConnectAPI.addReply(postId,commentId,AppController.sharedPreferences.getString("userId",""),AppController.sharedPreferences.getString("name",""),writeReply_et.getText().toString(),"https://cdn4.vectorstock.com/i/1000x1000/84/68/hipster-man-in-glasses-avatar-profile-userpic-on-vector-8988468.jpg");
        final Call<AddCommentResponse> replyCall=iConnectAPI.addReply(postId,commentId,"1","Sample name",writeReply_et.getText().toString(),"https://cdn4.vectorstock.com/i/1000x1000/84/68/hipster-man-in-glasses-avatar-profile-userpic-on-vector-8988468.jpg");
        progressBarUtility.displayProgress("Replying...");
        replyCall.enqueue(new Callback<AddCommentResponse>() {
            @Override
            public void onResponse(Call<AddCommentResponse> call, Response<AddCommentResponse> response) {
                if(null!=response.body()){
                    if(response.body().isStatus()){
                        NestedCommentsList nestedCommentsList=new NestedCommentsList("",AppController.sharedPreferences.getString("userImage",""),writeReply_et.getText().toString(),AppController.sharedPreferences.getString("userId","1"),AppController.sharedPreferences.getString("name","Sample name"));
                        nestedCommentsListList.add(nestedCommentsList);
                        progressBarUtility.cancelDialog();
                        writeReply_et.setText("");
                        Toast.makeText(NestedCommentActivity.this, "Replied succesfully.", Toast.LENGTH_SHORT).show();
                        replyCommentAdapter.notifyDataSetChanged();
                    }else{
                        progressBarUtility.cancelDialog();
                        Toast.makeText(NestedCommentActivity.this, "Reply failed.Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressBarUtility.cancelDialog();
                    Toast.makeText(NestedCommentActivity.this, "Unable to reply.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCommentResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(NestedCommentActivity.this, "Unable to reach the server.Please try again after sometime.", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:finish();
            break;
        }
    }


}
