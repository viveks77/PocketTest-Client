package com.example.pockettest.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;
import com.example.pockettest.Adapters.QuizRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private List<Quiz> quizList;
    private RecyclerView recyclerView;
    private QuizRecyclerViewAdapter quizRecyclerViewAdapter;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        quizList = new ArrayList<>();
        quizList = setCatInfo();

        quizRecyclerViewAdapter = new QuizRecyclerViewAdapter(quizList);
        recyclerView.setAdapter(quizRecyclerViewAdapter);
    }

    private List<Quiz> setCatInfo() {
        quizList.clear();
        for(int i = 0; i < 10; i++){
            Quiz quiz = new Quiz();
            quiz.setTitle("Quiz no :" + i);
            quiz.setDescription("This is quiz Description");
            quizList.add(quiz);
        }
        return quizList;
    }
}
