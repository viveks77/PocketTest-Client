package com.example.pockettest.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.pockettest.Adapters.OnListAdapter;
import com.example.pockettest.Adapters.UpListAdapter;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;
import com.example.pockettest.Adapters.QuizRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ArrayList<Quiz> upquizList;
    private RecyclerView ongoing_rv;
    private RecyclerView upcoming_rv;
    private QuizRecyclerViewAdapter quizRecyclerViewAdapter;
    private ArrayList<Quiz> quiz_list;

    Context thiscontext;  

    LinearLayoutManager layoutManagerGroup;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thiscontext=view.getContext();
        ongoing_rv=view.findViewById(R.id.ongoing_rv);
        quiz_list=new ArrayList<>();

        quiz_list.add(new Quiz("Physics","Rutherford Model"));
        OnListAdapter adapter=new OnListAdapter(quiz_list);


        //Upcoming Tests
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        ongoing_rv.setLayoutManager(layoutManager);
        ongoing_rv.setAdapter(adapter);

        upquizList=new ArrayList<>();
        upcoming_rv=view.findViewById(R.id.upcoming_rv);
        upquizList.add(new Quiz("Maths","Integration"));
        upquizList.add(new Quiz("Maths","Integration"));
        upquizList.add(new Quiz("Maths","Integration"));
        upquizList.add(new Quiz("Maths","Integration"));
        UpListAdapter upListAdapter=new UpListAdapter(upquizList);
        RecyclerView.LayoutManager uplayoutManager=new LinearLayoutManager(getContext());
        upcoming_rv.setLayoutManager(uplayoutManager);
        upcoming_rv.setAdapter(upListAdapter);




    }

}
