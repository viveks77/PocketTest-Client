package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.pockettest.DataBase.SharedPrefManager;
import com.example.pockettest.DataBase.UserDataBaseHandler;
import com.example.pockettest.Model.User;
import com.example.pockettest.R;
import com.example.pockettest.Util.Constants;
import com.example.pockettest.Util.Urls;
import com.example.pockettest.Util.VolleySingleton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPass;
    private Button loginB;
    private TextView forgotpassB,signupB;
    private UserDataBaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        db = new UserDataBaseHandler(this);
        editTextEmail = findViewById(R.id.email);
        editTextPass = findViewById(R.id.password);
        loginB = findViewById(R.id.loginB);
        forgotpassB = findViewById(R.id.forgot_pass);
        signupB = findViewById(R.id.signupB);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( validateData() )
                {
                    login();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private Boolean validateData() {

        if(editTextEmail.getText().toString().isEmpty()) {
            editTextEmail.setError("Enter E-mail");
            return false;
        }
        if(editTextPass.getText().toString().isEmpty()) {
            editTextPass.setError("Enter Password");
            return false;
        }
        return true;
    }

    private void login() {
        final String email = editTextEmail.getText().toString();
        final String password = editTextPass.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.BASE_URL + Urls.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject  obj = new JSONObject(response);

                        JSONObject userObj = obj.getJSONObject("user");

                        User user = new User();
                        user.setEmail(userObj.getString("email"));
                        user.setName(userObj.getString("name"));
                        user.setMobileNo(userObj.getString("mobile_no"));
                        user.setLocation(userObj.getString("location"));

                        //adding user to database
                        db.addUser(user);

                        //setting up Auth token
                        String token = obj.getString("token");
                        SharedPrefManager.getInstance(LoginActivity.this).generateToken(token);

                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));

                        JSONObject obj = new JSONObject(res);
                        JSONArray errorArray = obj.getJSONArray("non_field_errors");
                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Incorrect Credentials", Snackbar.LENGTH_SHORT).show();

                    } catch (UnsupportedEncodingException e1) {

                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Oops! Something went wrong", Snackbar.LENGTH_SHORT).show();
                        e1.printStackTrace();
                    } catch (JSONException e2) {

                        Log.d("error message", "returned data is not JSONObject?");
                        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),"Oops! Something went wrong", Snackbar.LENGTH_SHORT).show();
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

