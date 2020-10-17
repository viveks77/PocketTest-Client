package com.example.pockettest.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Adapters.MainFAdapter;
import com.example.pockettest.Model.Subjects;
import com.example.pockettest.R;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    List<Subjects> subjectsList;
    Context context;

    public MainFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=view.getContext();
        subjectsList = new ArrayList<>();
        subjectsList.add(new Subjects("Maths", R.drawable.exam_img));
        subjectsList.add(new Subjects("Science", R.drawable.exam_img));
        subjectsList.add(new Subjects("History", R.drawable.exam_img));
        subjectsList.add(new Subjects("Maths", R.drawable.exam_img));
        subjectsList.add(new Subjects("Science", R.drawable.exam_img));
        subjectsList.add(new Subjects("History", R.drawable.exam_img));

        RecyclerView mainfrv=(RecyclerView) view.findViewById(R.id.mainf_rv);

        MainFAdapter mainFAdapter=new MainFAdapter(context,subjectsList);
        mainfrv.setLayoutManager(new GridLayoutManager(context,2));
        mainfrv.setAdapter(mainFAdapter);







    }

}
