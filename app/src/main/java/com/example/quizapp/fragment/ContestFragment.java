package com.example.quizapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Icommunicator;
import com.example.quizapp.R;
import com.example.quizapp.activity.StartContestActivity;
import com.example.quizapp.adapter.ContestCardViewAdapter;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.request.UserGetAllContestRequest;
import com.example.quizapp.models.response.GetAllContestResponse;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestFragment extends Fragment implements Icommunicator {

    ArrayList logos = new ArrayList<>(Arrays.asList(R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon));
    UserGetAllContestRequest userGetAllContestRequest = new UserGetAllContestRequest();
    private RecyclerView contestrecyclerView;
    private GridLayoutManager gridLayoutManager;
    private GetAllContestResponse getAllContestResponses = new GetAllContestResponse();
    private IConnectAPI iConnectAPI;
    private ContestCardViewAdapter contestCardViewAdapter;
    private String userId = "u1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contest, container, false);
        contestrecyclerView = view.findViewById(R.id.main_screen_fragment_contest_recycler_view);
        gridLayoutManager = new GridLayoutManager(contestrecyclerView.getContext(), 2);
        contestrecyclerView.setLayoutManager(gridLayoutManager);
        userGetAllContestRequest.setUserId(userId);

        iConnectAPI = AppController.retrofit.create(IConnectAPI.class);
        iConnectAPI.getAllContest(userGetAllContestRequest).enqueue(new Callback<GetAllContestResponse>() {
            @Override
            public void onResponse(Call<GetAllContestResponse> call, Response<GetAllContestResponse> response) {

                if (response.isSuccessful()) {
                    getAllContestResponses = response.body();

                }
                contestCardViewAdapter = new ContestCardViewAdapter(getAllContestResponses.getResponse(), logos, ContestFragment.this);
                contestrecyclerView.setAdapter(contestCardViewAdapter);
                contestCardViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<GetAllContestResponse> call, Throwable t) {
                Log.e("Failure", t.getMessage());

            }
        });
        return view;
    }

    @Override
    public void navigate(int position) {
        Intent intent = new Intent(getContext(), StartContestActivity.class);
        intent.putExtra("contestId", getAllContestResponses.getResponse().get(position).getContestId());
        startActivity(intent);
    }

}

