package com.example.quizapp;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

public class Video_check extends AppCompatActivity {
    VideoView video;
    String video_url = "https://www.demonuts.com/Demonuts/smallvideo.mp4";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_video);
        video = (VideoView)findViewById(R.id.videoView);

        pd = new ProgressDialog(Video_check.this);
        pd.setMessage("Buffering video please wait...");
        pd.show();

    Uri uri = Uri.parse(video_url);
    video.setVideoURI(uri);
    video.requestFocus();

    video.start();

    video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            //close the progress dialog when buffering is done
            pd.dismiss();
        }
    });
}
    }

