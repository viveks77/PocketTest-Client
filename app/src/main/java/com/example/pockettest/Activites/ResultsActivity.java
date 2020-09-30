package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pockettest.R;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        TextView tv;
        Button btnNext;
        RadioGroup rg;
        RadioButton rb1, rb2, rb3,rb4;



            tv = (TextView) findViewById(R.id.test_question);
            rb1 = (RadioButton) findViewById(R.id.test_answer_1);
            rb2 = (RadioButton) findViewById(R.id.test_answer_2);
            rb3 = (RadioButton) findViewById(R.id.test_answer_3);
            rb4 =(RadioButton)findViewById(R.id.test_answer_4);



    }
}