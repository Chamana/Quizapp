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
import com.example.quizapp.models.request.SignupRequest;
import com.example.quizapp.models.response.LoginResponse;
import com.example.quizapp.models.response.NotificationTokenResponse;
import com.example.quizapp.models.response.SignupResponse;
import com.example.quizapp.utils.AppController;
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

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back_iv;
    private EditText name_et,email_et,password_et;
    private AppCompatButton signup_btn;
    private TextView login_tv;
    private SpannableString spannableString;
    private ClickableSpan clickableSpan;
    private ProgressBarUtility progressBarUtility;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        setInit();
    }

    private void init() {

        progressBarUtility=new ProgressBarUtility(this);
        back_iv=findViewById(R.id.back_iv);
        name_et=findViewById(R.id.name_et);
        email_et=findViewById(R.id.email_et);
        password_et=findViewById(R.id.password_et);
        signup_btn=findViewById(R.id.signup_btn);
        login_tv=findViewById(R.id.login_tv);
        spannableString=new SpannableString("Already have account? Sign in");


    }
    private void setInit() {
        setClickableSpan();
        back_iv.setOnClickListener(this);
        signup_btn.setOnClickListener(this);
    }

    private void setClickableSpan() {
        clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class).putExtra("from","signup"));
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        spannableString.setSpan(clickableSpan,22,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login_tv.setText(spannableString);
        login_tv.setMovementMethod(new LinkMovementMethod());
        login_tv.setHighlightColor(Color.TRANSPARENT);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv: finish();
                break;
            case R.id.signup_btn:validateFields();
                break;
        }
    }

    private void validateFields() {
        if(TextUtils.isEmpty(name_et.getText().toString())){
            name_et.setError("Please enter the name.");
        }
        if(TextUtils.isEmpty(email_et.getText().toString())){
            email_et.setError("Please enter the email id.");
        }
        if(TextUtils.isEmpty(password_et.getText().toString())){
            password_et.setError("Please enter the password.");
        }
        if(!TextUtils.isEmpty(name_et.getText().toString()) && !TextUtils.isEmpty(email_et.getText().toString()) && !TextUtils.isEmpty(password_et.getText().toString())){
            if(!Pattern.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9.]+\\.[A-Za-z]{2,6}$",email_et.getText().toString())){
                email_et.setError("Please enter valid email id.");
            }else if(password_et.getText().toString().length()<8){
                password_et.setError("Password must contain atleast 8 characters.");
            }else{
                SignupRequest signupRequest=new SignupRequest(name_et.getText().toString(),email_et.getText().toString(),password_et.getText().toString(),"user");
                signup(signupRequest);
            }
        }

    }

    private void signup(SignupRequest signupRequest) {
        IConnectAPI iConnectAPI= AppController.auth_retrofit.create(IConnectAPI.class);

        Call<SignupResponse> signupCall=iConnectAPI.signup(signupRequest);
        progressBarUtility.displayProgress("Creating account.Please wait...");
        signupCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                try {
                    if (response.body().isStatus()) {
                        SharedPreferences.Editor editor = AppController.sharedPreferences.edit();
                        editor.putString("name", response.body().getName());
                        editor.putString("userId", response.body().getUserId());
                        editor.putString("email", response.body().getUserName());
                        Log.d("userId", response.body().getUserId());
                        editor.commit();
                        getUserToken();
                        progressBarUtility.cancelDialog();
                    } else {
                        progressBarUtility.cancelDialog();
                        Toast.makeText(SignupActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    progressBarUtility.cancelDialog();
                    Toast.makeText(SignupActivity.this, "Error occured.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(SignupActivity.this, "Unable to reach the server.Please try again after sometime.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserToken() {
        IConnectAPI iConnectAPI=AppController.auth_retrofit.create(IConnectAPI.class);
        LoginRequest request=new LoginRequest(email_et.getText().toString(),password_et.getText().toString());
        Call<LoginResponse> loginCall=iConnectAPI.login(request);
        progressBarUtility.displayProgress("Setting account...");
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.body()==null){
                    progressBarUtility.cancelDialog();
                    Toast.makeText(SignupActivity.this, "Error occurred.", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor=AppController.sharedPreferences.edit();
                    editor.putString("token",response.body().getAuthorization());
                    editor.putBoolean("isLoggedIn",true);
                    editor.commit();
                    progressBarUtility.cancelDialog();
                    getNotificationToken();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(SignupActivity.this, "Unable to connect to server.Please try again.", Toast.LENGTH_SHORT).show();
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
                    startActivity(new Intent(SignupActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                }else{
                    progressBarUtility.cancelDialog();
                    Toast.makeText(SignupActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationTokenResponse> call, Throwable t) {
                progressBarUtility.cancelDialog();
                Toast.makeText(SignupActivity.this, "Unable to reach the server.Please try again." + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
