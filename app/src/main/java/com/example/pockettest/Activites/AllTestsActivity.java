package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pockettest.R;

public class AllTestsActivity extends AppCompatActivity {
    private RecyclerView rvall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tests);

    }
}