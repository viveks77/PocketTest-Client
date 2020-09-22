package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pockettest.R;

public class StartTest extends AppCompatActivity {
    private Button starttest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);
        starttest=findViewById(R.id.button_starttest);
        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starttest();
            }
        });
    }
    private void starttest(){
        Intent intent=new Intent(StartTest.this,MainActivity.class);
        startActivity(intent);
    }

}