package com.example.quizapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.R;
import com.example.quizapp.adapter.ContestCardViewAdapter;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.Response.GetAllContestResponse;
import com.example.quizapp.models.Response.ResponseItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContestFragment extends Fragment {

    ArrayList logos = new ArrayList<>(Arrays.asList(R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon));
    private RecyclerView contestrecyclerView;
    private GridLayoutManager gridLayoutManager;
    private GetAllContestResponse getAllContestResponses;
    private List<ResponseItem> contestResponseList=new ArrayList<>();
    private IConnectAPI iConnectAPI;

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
        final ContestCardViewAdapter contestCardViewAdapter = new ContestCardViewAdapter(contestResponseList, logos);
        contestrecyclerView.setAdapter(contestCardViewAdapter);
        iConnectAPI = AppController.retrofitContest.create(IConnectAPI.class);
        iConnectAPI.getAllContest().enqueue(new Callback<GetAllContestResponse>() {
            @Override
            public void onResponse(Call<GetAllContestResponse> call, Response<GetAllContestResponse> response) {

                if (response.isSuccessful()) {
                    getAllContestResponses = response.body();

                }
                contestResponseList.clear();
                contestResponseList.addAll(getAllContestResponses.getResponse());
                contestCardViewAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<GetAllContestResponse> call, Throwable t) {

            }
        });
        return view;
    }

}

