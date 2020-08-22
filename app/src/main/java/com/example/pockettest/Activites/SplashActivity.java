package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pockettest.R;

public class SplashActivity extends AppCompatActivity {
    private TextView appname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appname=findViewById(R.id.app_name);
        new Thread()
        {

            @Override
            public void run()
            {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent= new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }


        }.start();

    }
}
