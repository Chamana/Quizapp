package com.example.quizapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressBarUtility {
    Context context;
    ProgressDialog progressDialog;

    public ProgressBarUtility(Context context) {
        this.context = context;
        progressDialog=new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    public void displayProgress(String message){
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void displayCancellableProgress(String message){
        progressDialog.setCancelable(true);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void  cancelDialog(){
        progressDialog.dismiss();
    }
}
