package com.example.quizapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.quizapp.R;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.request.CreatePostRequest;
import com.example.quizapp.models.Response.CreatePostResponse;
import com.example.quizapp.utils.ProgressBarUtility;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_iv,photo_iv;
    private VideoView video_vv;
    private EditText caption_et;
    private LinearLayout type_ll;
    private TextView text_tv,photo_tv,video_tv,post_tv;
    private final int PHOTO_CODE=1;
    private final int VIDEO_CODE=2;
    private ProgressBarUtility progressBarUtility;
    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        init();
        setInit();
        getPermission();
    }

    private void init(){
        back_iv=findViewById(R.id.back_iv);
        photo_iv=findViewById(R.id.photo_iv);
        video_vv=findViewById(R.id.video_vv);
        caption_et=findViewById(R.id.caption_et);
        type_ll=findViewById(R.id.type_ll);
        text_tv=findViewById(R.id.text_tv);
        photo_tv=findViewById(R.id.photo_tv);
        video_tv=findViewById(R.id.video_tv);
        post_tv=findViewById(R.id.post_tv);
        progressBarUtility=new ProgressBarUtility(this);
    }

    private void setInit() {
        back_iv.setOnClickListener(this);
        text_tv.setOnClickListener(this);
        photo_tv.setOnClickListener(this);
        video_tv.setOnClickListener(this);
        post_tv.setOnClickListener(this);
    }

    private void getPermission() {
        if(ActivityCompat.checkSelfPermission(CreatePostActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CreatePostActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CreatePostActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //External write permission is granted you can proceed.
                Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
            }else{
                getPermission();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:break;
            case R.id.text_tv:  setPostType("text");
                                break;
            case R.id.photo_tv: setPostType("image");
                                Intent photoIntent=new Intent(Intent.ACTION_PICK);
                                photoIntent.setType("image/*");
                                startActivityForResult(Intent.createChooser(photoIntent,"Select image"),PHOTO_CODE);
                                break;
            case R.id.video_tv: setPostType("video");
                                Intent videoIntent=new Intent(Intent.ACTION_PICK);
                                videoIntent.setType("video/*");
                                startActivityForResult(Intent.createChooser(videoIntent,"Select video"),VIDEO_CODE);
                                break;
            case R.id.post_tv:  if(type_ll.getTag().equals("text")) {
                                    if(!caption_et.getText().toString().equals("")) {
                                        CreatePostRequest request = new CreatePostRequest("1", "text", caption_et.getText().toString(), null);
                                        addPost(request);
                                    }else{
                                        Toast.makeText(this, "Please enter something to post.", Toast.LENGTH_SHORT).show();
                                    }
                                }else if(type_ll.getTag().equals("image")){
                                    setUpFirebaseImage(uri);
                                }else{
                                    setUpFirebaseVideo(uri);
                                }
                                break;
        }
    }
    public void setPostType(String type){
        type_ll.setTag(type);
        switch (type){
            case "text":    caption_et.setText("");
                            photo_iv.setVisibility(View.GONE);
                            video_vv.setVisibility(View.GONE);
                            text_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_selected));
                            text_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            photo_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_unselected));
                            photo_tv.setTextColor(getResources().getColor(R.color.colorAccent));
                            video_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_unselected));
                            video_tv.setTextColor(getResources().getColor(R.color.colorAccent));
                            break;
            case "image":   caption_et.setText("");
                            video_vv.setVisibility(View.GONE);
                            text_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_unselected));
                            text_tv.setTextColor(getResources().getColor(R.color.colorAccent));
                            photo_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_selected));
                            photo_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            video_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_unselected));
                            video_tv.setTextColor(getResources().getColor(R.color.colorAccent));
                            break;
            case "video":   caption_et.setText("");
                            photo_iv.setVisibility(View.GONE);
                            text_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_unselected));
                            text_tv.setTextColor(getResources().getColor(R.color.colorAccent));
                            photo_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_unselected));
                            photo_tv.setTextColor(getResources().getColor(R.color.colorAccent));
                            video_tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.interest_bgk_selected));
                            video_tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_CODE:    if(resultCode==RESULT_OK && data!=null){
                                    photo_iv.setVisibility(View.VISIBLE);
                                    uri=data.getData();
                                    try {
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                        photo_iv.setImageBitmap(bitmap);
                                    }catch (Exception e){
                                        Log.e("Error",e.toString());
                                    }
                                }else{
                                    setPostType("text");
                                }
                break;
            case VIDEO_CODE:if(resultCode==RESULT_OK && data!=null){
                                uri=data.getData();
                                video_vv.setVisibility(View.VISIBLE);
                                video_vv.setVideoURI(uri);
                                video_vv.requestFocus();
                                video_vv.start();
//                                    photo_iv.setImageBitmap(BitmapFactory.decodeFile(filePath));
                            }else{
                                setPostType("text");
                            }
                            break;
        }
    }

    private void setUpFirebaseVideo(Uri uri) {
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference storageReference=storage.getReference();
        String fileName="video"+(Math.random()*22+1);
        StorageReference videoRef=storageReference.child("/videoPosts/"+fileName);
        progressBarUtility.displayProgress("Uploading video");
        uploadVideo(videoRef,uri);
    }

    private void uploadVideo(final StorageReference videoRef, Uri uri) {
        if(uri!=null){
            Task<Uri> urlTask = videoRef.putFile(uri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return videoRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        progressBarUtility.cancelDialog();
                        CreatePostRequest request=new CreatePostRequest("1",type_ll.getTag().toString(),caption_et.getText().toString(),downloadUri.toString());
                        addPost(request);
                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });
        }else{
            Toast.makeText(this, "Nothing to upload.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setUpFirebaseImage(Uri uri){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        final StorageReference storageRef = storage.getReference();
        final String fileName="askjfbaksdhgfjkahsdf"+(Math.random()*50+1);
        final StorageReference postRef = storageRef.child("/postImages/"+fileName);
//
        photo_iv.setDrawingCacheEnabled(true);
        photo_iv.buildDrawingCache();
        try{
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            photo_iv.setImageBitmap(bitmap);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
            final byte[] data = baos.toByteArray();
            progressBarUtility.displayProgress("Uploading photo...");
            uploadImage(postRef,data);
        }catch (Exception e){
            progressBarUtility.cancelDialog();
            Toast.makeText(this, "Error occured.", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImage(final StorageReference ref,byte[] data ){
        Task<Uri> urlTask = ref.putBytes(data).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    progressBarUtility.cancelDialog();
                    CreatePostRequest request=new CreatePostRequest("1",type_ll.getTag().toString(),caption_et.getText().toString(),downloadUri.toString());
                    addPost(request);
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }

    private void addPost(CreatePostRequest request){
        IConnectAPI iConnectAPI= AppController.posts_retrofit.create(IConnectAPI.class);
        Call<CreatePostResponse> postResponseCall=iConnectAPI.createPost(request);
        progressBarUtility.displayProgress("Sharing post.");
        postResponseCall.enqueue(new Callback<CreatePostResponse>() {
            @Override
            public void onResponse(Call<CreatePostResponse> call, Response<CreatePostResponse> response) {
                if(response.body().isStatus()){
                    caption_et.setText("");
                    setPostType("text");
                    progressBarUtility.cancelDialog();
                    Toast.makeText(CreatePostActivity.this, "Post shared successfully.", Toast.LENGTH_SHORT).show();
                }else{
                    progressBarUtility.cancelDialog();
                    Toast.makeText(CreatePostActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreatePostResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(CreatePostActivity.this, "Unable to connect to server.Please try again after sometime.", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
