package com.example.quizapp;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.ContestSubmit;
import com.example.quizapp.models.request.PutSkippedQuestion;
import com.example.quizapp.models.request.PutSubmitQuestion;
import com.example.quizapp.models.request.Request;
import com.example.quizapp.models.request.SubmitQuesBody;
import com.example.quizapp.models.Response.GetContestQuestion;
import com.example.quizapp.models.Response.GetContestQuestionBody;
import com.example.quizapp.models.Question;
import com.example.quizapp.models.Response.GetSkippedQuestion;
import com.example.quizapp.models.Response.OptionDTOListItem;
import com.example.quizapp.models.Response.SubmitQuesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContestPage extends AppCompatActivity {

    private TextView timer;
    private Button submit;
    private Button skip;
    private CountDownTimer countDownTimer;
    private boolean run;
    List<String> img = new ArrayList<String>();
    ImageView ques_iv;
    TextView ques_tv;
    VideoView ques_vv;
    TextView tv;
    //int i=0;
    OkHttpClient client;
    public static Retrofit retrofit;
    IConnectAPI iConnectAPI;
    Question question = new Question();
    long timerT;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;
    GetContestQuestionBody getContestQuestionBody;
    ContestSubmit contestSubmit;
    String ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_page);
        //setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));

        ques_iv = findViewById(R.id.ques_iv);
        ques_vv = findViewById(R.id.ques_vv);
        ques_tv = findViewById(R.id.ques_tv);
        cb1 = findViewById(R.id.checkBox);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        cb4 = findViewById(R.id.checkBox4);
        cb5 = findViewById(R.id.checkBox5);
        img.add("A");
        img.add("data:image/jpeg;base64,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");
        img.add("https://www.demonuts.com/Demonuts/smallvideo.mp4");
        tv = findViewById(R.id.text);
        timer = findViewById(R.id.tv_ques_timer);
        submit = findViewById(R.id.submit);
        skip = findViewById(R.id.skip);

        iConnectAPI = AppController.static_contest_retrofit.create(IConnectAPI.class);

        getContestQuestionBody = new GetContestQuestionBody("android");

        //nextQues(iConnectAPI)
        ;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skip();
                //Toast.makeText(ContestPage.this, "Next" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        nextQues(iConnectAPI);

    }

    public void startStop() throws IOException {
        if (run)
            stopTimer();
        startTimer();

    }

    public void startTimer() {

        countDownTimer = new CountDownTimer(timerT, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerT = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                //nextQues(iConnectAPI);
                submit();
            }
        }.start();

        run = true;
    }

    public void stopTimer() {
        countDownTimer.cancel();

        run = false;
    }

    public void updateTimer() {
        int minutes = (int) timerT / 60000;
        int seconds = (int) timerT % 60000 / 1000;

        String timeLeftText = "" + minutes + ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        timer.setText(timeLeftText);

    }

    public void alertDialogue(final IConnectAPI iConnectAPI) {
        AlertDialog alertDialog = new AlertDialog.Builder(ContestPage.this).create();
        alertDialog.setTitle("Quiz");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Next Question", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nextQues(iConnectAPI);
                Toast.makeText(ContestPage.this, "Next", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(ContestPage.this, "Exit", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void skippedAlertDialogue(final IConnectAPI iConnectAPI) {
        AlertDialog alertDialog = new AlertDialog.Builder(ContestPage.this).create();
        alertDialog.setTitle("Skipped ques left");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Show Skipped Question", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                skippedQues(iConnectAPI);
                Toast.makeText(ContestPage.this, "Next", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(ContestPage.this, "Exit", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Submit Contest", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                submitContest(iConnectAPI);
                Toast.makeText(ContestPage.this, "Submit", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void endAlertDialogue(final IConnectAPI iConnectAPI) {

        AlertDialog alertDialog = new AlertDialog.Builder(ContestPage.this).create();
        alertDialog.setTitle("No skipped ques left");
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Submit Contest", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                submitContest(iConnectAPI);
                Toast.makeText(ContestPage.this, "Submit", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void submitContest(final IConnectAPI iConnectAPI) {
        iConnectAPI.submitContest("45d42091-b602-47a7-9c91-dae9e3b69488", getContestQuestionBody).enqueue(new Callback<SubmitQuesResponse>() {
            @Override
            public void onResponse(Call<SubmitQuesResponse> call, Response<SubmitQuesResponse> response) {
                Log.d("SUBMIT RESPONSE: ", response.body().toString());
                if (response.body().getSubmitResponse() != null) {
                    contestSubmit.setErrorMsg(response.body().getErrorMessage());
                    contestSubmit.setActive(response.body().getSubmitResponse().getContest().isActive());
                    contestSubmit.setCategoryId(response.body().getSubmitResponse().getContest().getCategoryId());
                    contestSubmit.setContestId(response.body().getSubmitResponse().getContest().getContestId());
                    contestSubmit.setDifficulty(response.body().getSubmitResponse().getContest().getDifficulty());
                    contestSubmit.setName(response.body().getSubmitResponse().getContest().getName());
                    contestSubmit.setNoOfQues(response.body().getSubmitResponse().getContest().getNoOfQuestions());
                    contestSubmit.setSkips(response.body().getSubmitResponse().getContest().getSkips());
                    contestSubmit.setType(response.body().getSubmitResponse().getContest().getType());
                    contestSubmit.setContestSub(response.body().getSubmitResponse().getContestsubscribedId());
                    contestSubmit.setFinished(response.body().getSubmitResponse().isFinished());
                    contestSubmit.setScore(response.body().getSubmitResponse().getScore());
                    contestSubmit.setUserId(response.body().getSubmitResponse().getUserId());
                    contestSubmit.setStatus(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<SubmitQuesResponse> call, Throwable t) {

            }
        });
    }

    public void nextQues(final IConnectAPI iConnectAPI) {


        iConnectAPI.getContestQuestion("45d42091-b602-47a7-9c91-dae9e3b69488", getContestQuestionBody).enqueue(new Callback<GetContestQuestion>() {
            @Override
            public void onResponse(Call<GetContestQuestion> call, Response<GetContestQuestion> response) {
                Log.d("PLAY_AREA: ", response.body().toString());
                if (response.body().getStatus().equals("failure") && response.body().getErrorMessage().equals("check skipped questions"))
                    skippedAlertDialogue(iConnectAPI);
                else if (response.body().getQuesResponse() != null) {
                    skip.setVisibility(View.VISIBLE);
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    cb4.setChecked(false);
                    cb5.setChecked(false);
                    question.setErrorMsg(response.body().getErrorMessage());
                    question.setAnsType(response.body().getQuesResponse().getAnswerType());
                    question.setDuration(response.body().getQuesResponse().getDuration());
                    List<String> optionContentList = new ArrayList<>();
                    List<String> optionIdList = new ArrayList<>();
                    for (OptionDTOListItem optionDTOListItem : response.body().getQuesResponse().getOptionDTOList()) {
                        optionContentList.add(optionDTOListItem.getOptionContent());
                        optionIdList.add(optionDTOListItem.getOptionId());
                    }
                    question.setOptionContent(optionContentList);
                    question.setOptionId(optionIdList);
                    question.setQuesCat(response.body().getQuesResponse().getQuestionCategory());
                    question.setQuesContent(response.body().getQuesResponse().getQuestionContent());
                    question.setQuesDifficulty(response.body().getQuesResponse().getQuestionDifficulty());
                    question.setQuesId(response.body().getQuesResponse().getQuestionId());
                    question.setQuesName(response.body().getQuesResponse().getQuestionName());
                    question.setQuesType(response.body().getQuesResponse().getQuestionType());
                    question.setStatus(response.body().getStatus());
                    timerT = question.getDuration() * 1000;

                    try {
                        updateUI(question);
                        startStop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetContestQuestion> call, Throwable t) {
                Toast.makeText(ContestPage.this, "***" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void submit() {
        System.out.println("submit");

        String optionIds = getSelectedOptions();
        Log.d("OPTION_SUBMIT", optionIds);
        Request request = new Request(question.getQuesId(), 1, optionIds);
        SubmitQuesBody submitQuesBody = new SubmitQuesBody(request, "android");
        System.out.println(submitQuesBody);

        Call<PutSubmitQuestion> call = iConnectAPI.putSubmitQuestion("45d42091-b602-47a7-9c91-dae9e3b69488", "" + question.getQuesId(), submitQuesBody);
        call.enqueue(new Callback<PutSubmitQuestion>() {
            @Override
            public void onResponse(Call<PutSubmitQuestion> call, Response<PutSubmitQuestion> response) {

                System.out.println(" question submitted");
                //nextQues(iConnectAPI);
                Log.d("SUBMIT", response.body().toString());
                if (response.body().getStatus().equals("success"))
                    alertDialogue(iConnectAPI);
                else

                    Toast.makeText(ContestPage.this, "fail" + response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PutSubmitQuestion> call, Throwable t) {

                System.out.println(t.getMessage());

            }
        });
    }

    private String getSelectedOptions() {
        StringBuilder res = new StringBuilder();
        if (cb1.isChecked()) {
            res.append(question.getOptionId().get(0) + ",");
        }
        if (cb2.isChecked()) {
            res.append(question.getOptionId().get(1) + ",");
        }
        if (cb3.isChecked()) {
            res.append(question.getOptionId().get(2) + ",");
        }
        if (cb4.isChecked()) {
            res.append(question.getOptionId().get(3) + ",");
        }
        return res.toString();
    }

    public void skip() {
        iConnectAPI.putSkippedQuestion("45d42091-b602-47a7-9c91-dae9e3b69488", "" + question.getQuesId(), getContestQuestionBody).enqueue(new Callback<PutSkippedQuestion>() {
            @Override
            public void onResponse(Call<PutSkippedQuestion> call, Response<PutSkippedQuestion> response) {
                Log.d("SKIPPED", response.body().toString());
                if (response.body().getStatus().equals("success"))
                { alertDialogue(iConnectAPI);
                skip.setVisibility(View.GONE);
                }
                else
                    Toast.makeText(ContestPage.this, response.body().getErrorMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PutSkippedQuestion> call, Throwable t) {

            }
        });
    }

    public void skippedQues(final IConnectAPI iConnectAPI) {
        Log.d("SKIP", "skipQscalled()");
        iConnectAPI.getSkippedQuestion("45d42091-b602-47a7-9c91-dae9e3b69488", getContestQuestionBody).enqueue(new Callback<GetSkippedQuestion>() {
            @Override
            public void onResponse(Call<GetSkippedQuestion> call, Response<GetSkippedQuestion> response) {
                Log.d("SKIP", response.body().toString());
                if (response.body().getStatus().equals("failure") && response.body().getErrorMessage().equals("No Skipped Questions Availaible"))
                    endAlertDialogue(iConnectAPI);
                else if (response.body().getQuesResponse() != null) {
                    System.out.println(response.body());
                    question.setErrorMsg(response.body().getErrorMessage());
                    question.setAnsType(response.body().getQuesResponse().getAnswerType());
                    question.setDuration(response.body().getQuesResponse().getDuration());
                    List<String> optionContentList = new ArrayList<>();
                    List<String> optionIdList = new ArrayList<>();
                    for (OptionDTOListItem optionDTOListItem : response.body().getQuesResponse().getOptionDTOList()) {
                        optionContentList.add(optionDTOListItem.getOptionContent());
                        optionIdList.add(optionDTOListItem.getOptionId());
                    }
                    question.setOptionContent(optionContentList);
                    question.setOptionId(optionIdList);
                    question.setQuesCat(response.body().getQuesResponse().getQuestionCategory());
                    question.setQuesContent(response.body().getQuesResponse().getQuestionContent());
                    question.setQuesDifficulty(response.body().getQuesResponse().getQuestionDifficulty());
                    question.setQuesId(response.body().getQuesResponse().getQuestionId());
                    question.setQuesName(response.body().getQuesResponse().getQuestionName());
                    question.setQuesType(response.body().getQuesResponse().getQuestionType());
                    question.setStatus(response.body().getStatus());
                    timerT = question.getDuration() * 1000;
                    try {
                        updateUI(question);
                        startStop();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetSkippedQuestion> call, Throwable t) {

            }
        });
    }

    public void updateUI(Question question) throws IOException {
        Log.d("updateUI", question.toString());

        if (question.getQuesType().equals("Text")) {
            ques_vv.setVisibility(View.GONE);
            ques_iv.setVisibility(View.GONE);
            ques_tv.setVisibility(View.VISIBLE);
            ques_tv.setText(question.getQuesName());


        } else if (question.getQuesType().equals("Image")) {
            ques_vv.setVisibility(View.GONE);
            ques_tv.setVisibility(View.GONE);
            ques_iv.setVisibility(View.VISIBLE);
            String img2 = question.getQuesContent();
//            String img2="https://static.standard.co.uk/s3fs-public/thumbnails/image/2018/12/17/09/lionelmessi1712.jpg";
            // Log.d("Image:",img2);
            //Toast.makeText(ContestPage.this, "" + img2, Toast.LENGTH_SHORT).show();
            Glide.with(this).load(img2).apply(new RequestOptions().override(500, 500)).into(ques_iv);
        } else if (question.getQuesType().equals("Video")) {
            ques_iv.setVisibility(View.GONE);
            ques_tv.setVisibility(View.GONE);
            ques_vv.setVisibility(View.VISIBLE);
            if (ques_vv != null) {
                ques_vv.setVideoURI(Uri.parse(question.getQuesContent()));
                ques_vv.requestFocus();
                ques_vv.start();
            }
        } else if (question.getQuesType().equals("Audio")) {
            ques_iv.setVisibility(View.GONE);
            ques_vv.setVisibility(View.GONE);
            ques_tv.setVisibility(View.VISIBLE);
            ques_tv.setText("Listen to audio...");
            Uri myUri = Uri.parse(question.getQuesContent());

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getApplicationContext(), myUri);
            mediaPlayer.prepare();
            mediaPlayer.start();

        }

        try {
            cb1.setText(question.getOptionContent().get(0));
            cb2.setText(question.getOptionContent().get(1));
            cb3.setText(question.getOptionContent().get(2));
            cb4.setText(question.getOptionContent().get(3));
        } catch (Exception e) {
            Log.e("Option error", e.getMessage());
        }
        if (question.getQuesContent().length() == 5)
            cb5.setText(question.getOptionContent().get(4));
        else
            cb5.setVisibility(View.GONE);
    }

}
