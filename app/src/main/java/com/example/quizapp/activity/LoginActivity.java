package com.example.quizapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.request.LoginRequest;
import com.example.quizapp.models.Response.LoginResponse;
import com.example.quizapp.models.Response.NotificationTokenResponse;
import com.example.quizapp.models.Response.SignupResponse;
import com.example.quizapp.api.AppController;
import com.example.quizapp.utils.ProgressBarUtility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView back_iv;
    EditText email_et,password_et;
    AppCompatButton login_btn;
    TextView signup_tv;
    SpannableString spannableString;
    ClickableSpan clickableSpan;
    ProgressBarUtility progressBarUtility;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBarUtility=new ProgressBarUtility(this);
        back_iv=findViewById(R.id.back_iv);
        email_et=findViewById(R.id.email_et);
        password_et=findViewById(R.id.password_et);
        login_btn=findViewById(R.id.login_btn);
        signup_tv=findViewById(R.id.signup_tv);
        back_iv.setOnClickListener(this);
        login_btn.setOnClickListener(this);

        spannableString=new SpannableString("Don't have account? Signup");
        clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
//                Toast.makeText(LoginActivity.this, "Sample", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }

            @Override
            public void updateDrawState( TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        spannableString.setSpan(clickableSpan,20,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signup_tv.setText(spannableString);
        signup_tv.setMovementMethod(new LinkMovementMethod());
        signup_tv.setHighlightColor(Color.TRANSPARENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv: finish();
                break;
            case R.id.login_btn: validateFields();
                break;
        }
    }

    private void validateFields() {
        email_et.setError(null);
        password_et.setError(null);
        if(TextUtils.isEmpty(email_et.getText().toString())){
            email_et.setError("Please enter the Email.");
        }
        if(TextUtils.isEmpty(password_et.getText().toString())){
            password_et.setError("Please enter the password");
        }
        if(!TextUtils.isEmpty(email_et.getText().toString()) && !TextUtils.isEmpty(password_et.getText().toString())){
            if(!Pattern.matches("[a-zA-Z0-9_]+@[a-zA-Z0-9.]+\\.[A-Za-z]{2,6}$",email_et.getText().toString())){
                email_et.setError("Please enter a valid email id.");
            }else if(password_et.getText().toString().length()<8) {
                password_et.setError("Password must contain atleast 8 characters.");
            }else{
                LoginRequest loginRequest=new LoginRequest(email_et.getText().toString(),password_et.getText().toString());
                login(loginRequest);
            }
        }
    }

    private void login(LoginRequest loginRequest) {
        IConnectAPI iConnectAPI= AppController.auth_retrofit.create(IConnectAPI.class);
        Call<LoginResponse> call=iConnectAPI.login(loginRequest);
        progressBarUtility.displayProgress("Logging in.Please wait...");
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.body()==null){
                    progressBarUtility.cancelDialog();
                    Toast.makeText(LoginActivity.this, "Error occurred.", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor=AppController.sharedPreferences.edit();
                    editor.putString("token",response.body().getAuthorization());
                    editor.putBoolean("isLoggedIn",true);
                    editor.commit();
                    progressBarUtility.cancelDialog();
                    getUserDetails();
//                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(LoginActivity.this, "Unable to connect to server.Please try again.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getUserDetails() {
        IConnectAPI iConnectAPI=AppController.auth_retrofit.create(IConnectAPI.class);
        Call<SignupResponse> detailsCall=iConnectAPI.getUserDetails(AppController.sharedPreferences.getString("token",""),email_et.getText().toString());
        progressBarUtility.displayProgress("Fetching details.Please wait...");
        detailsCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if(response.body().isStatus()){
                    SharedPreferences.Editor editor=AppController.sharedPreferences.edit();
                    editor.putString("name",response.body().getName());
                    editor.putString("userId",response.body().getUserId());
                    editor.putString("email",response.body().getUserName());
                    editor.commit();
                    getNotificationToken();
                }else{
                    progressBarUtility.cancelDialog();
                    Toast.makeText(LoginActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Unable to connect to server.Please try again after sometime.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNotificationToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("FIREBASE", "getInstanceId failed", task.getException());
                    return;
                }
                // Get new Instance ID token
                String token = task.getResult().getToken();
                // Log and toast
                Log.d("FIREBASE", token);
                setNotificationToken(token);
            }
        });
    }

    private void setNotificationToken(String token) {
        FirebaseMessaging.getInstance().subscribeToTopic("contest").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Subscribed","Subscribed to topic success.");
            }
        });
        IConnectAPI iConnectAPI = AppController.notification_retrofit.create(IConnectAPI.class);
        Call<NotificationTokenResponse> setTokenCall = iConnectAPI.setNotificationToken(AppController.sharedPreferences.getString("userId",""), token);
        progressBarUtility.displayProgress("Configuring account.Please wait...");
        setTokenCall.enqueue(new Callback<NotificationTokenResponse>() {
            @Override
            public void onResponse(Call<NotificationTokenResponse> call, Response<NotificationTokenResponse> response) {
                if (response.body().getStatus().equalsIgnoreCase("success")) {
                    SharedPreferences.Editor editor = AppController.sharedPreferences.edit();
                    editor.putString("token", response.body().getAndroidDeviceId());
                    editor.commit();
                    progressBarUtility.cancelDialog();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }

            @Override
            public void onFailure(Call<NotificationTokenResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(LoginActivity.this, "Unable to reach the server.Please try again." + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
