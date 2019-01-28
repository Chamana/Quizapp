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

import java.util.ArrayList;
import java.util.Arrays;

public class ContestFragment extends Fragment {

    ArrayList logos = new ArrayList<>(Arrays.asList(R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon, R.drawable.splash_icon,
            R.drawable.splash_icon));
    ArrayList<String> nameList =new ArrayList(Arrays.asList("10", "11", "12", "13", "14", "15", "DD", "Divanshu", "Anshu", "Srivastava"));
    private RecyclerView contestrecyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_contest, container, false);
        contestrecyclerView=view.findViewById(R.id.main_screen_fragment_contest_recycler_view);
        gridLayoutManager = new GridLayoutManager(contestrecyclerView.getContext(), 2);
        contestrecyclerView.setLayoutManager(gridLayoutManager);
        ContestCardViewAdapter contestCardViewAdapter = new ContestCardViewAdapter(nameList, logos);
        contestrecyclerView.setAdapter(contestCardViewAdapter);
        return view;
    }

}

