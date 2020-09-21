package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pockettest.Adapters.TestAdapter;
import com.example.pockettest.Model.QuestionAns;
import com.example.pockettest.R;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    private ArrayList<QuestionAns> queans_list;
    private RecyclerView testrv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testrv=findViewById(R.id.test_rv);
        queans_list=new ArrayList<>();
        setinfo();
        setAdapter();
    }

    private void setAdapter() {
        TestAdapter testAdapter=new TestAdapter(queans_list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        testrv.setLayoutManager(layoutManager);
        testrv.setItemAnimator(new DefaultItemAnimator());
        testrv.setAdapter(testAdapter);
    }

    private void setinfo() {
        queans_list.add(new QuestionAns("Questioooooonnnn","a","b","c","d",2));
    }
}