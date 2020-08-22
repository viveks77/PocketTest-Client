package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pockettest.R;

public class LoginActivity extends AppCompatActivity {
    private EditText email,pass;
    private Button loginB;
    private TextView forgotpassB,signupB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        loginB=findViewById(R.id.loginB);
        forgotpassB=findViewById(R.id.forgot_pass);
        signupB=findViewById(R.id.signupB);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData())
                {
                    login();
                }
            }
        });
    }

    private Boolean validateData() {

        if(email.getText().toString().isEmpty()) {
            email.setError("Enter E-mail");
            return false;
        }
        if(pass.getText().toString().isEmpty()) {
            pass.setError("Enter Password");
            return false;
        }
        return true;
    }

    private void login() {
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        }
}

