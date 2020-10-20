package com.example.pockettest.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pockettest.Adapters.AllTestListRecyclerViewAdapter;
import com.example.pockettest.DataBase.SharedPrefManager;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;
import com.example.pockettest.Util.Urls;
import com.example.pockettest.Util.VolleySingleton;
import com.google.android.material.appbar.MaterialToolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllTestListActivity extends AppCompatActivity {
    private List<Quiz> quizList;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private TextView textView;
    private AllTestListRecyclerViewAdapter adapter;
    String slug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_test_list);
        toolbar = findViewById(R.id.activity_all_test_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            getSupportActionBar().setTitle(bundle.getString("name").toUpperCase());
            slug = bundle.getString("slug");
        }
        textView = findViewById(R.id.alltest_list_textview);
        recyclerView = findViewById(R.id.alltest_list_recyclerview);
        quizList = new ArrayList<>();
        getQuiz();
        if(quizList.size() == 0){
            textView.setVisibility(View.VISIBLE);
        }
        adapter = new AllTestListRecyclerViewAdapter(AllTestListActivity.this, quizList);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllTestListActivity.this));
        recyclerView.setAdapter(adapter);
    }

    public void getQuiz(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.BASE_URL + slug + Urls.GET_QUIZ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray parentArray = new JSONArray(response);
                    for (int i = 0; i < parentArray.length(); i++) {
                        JSONObject quizObj = parentArray.getJSONObject(i);
                        Quiz quiz = new Quiz();
                        quiz.setTitle(quizObj.getString("title"));
                        quiz.setDescription(quizObj.getString("description"));
                        quiz.setTotal_marks(quizObj.getString("total_marks"));
                        quiz.setPrimary_key(quizObj.getString("pk"));
                        quiz.setPublish_date(LocalDateTime.parse(quizObj.getString("publish_date")));
                        quiz.setEnd_time(LocalDateTime.parse(quizObj.getString("end_date")));
                        LocalDateTime dateTime = LocalDateTime.now();
                        int endTimeDiff = dateTime.compareTo(quiz.getEnd_time());
                        Log.d("datetime", String.valueOf(endTimeDiff));
                        if(endTimeDiff > 0){
                            quizList.add(quiz);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    textView.setVisibility(View.GONE);
                } catch (JSONException e) {
                    Log.d("error", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error response", error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SharedPrefManager.getInstance(AllTestListActivity.this).getToken());
                return headers;
            }
        };
        VolleySingleton.getInstance(AllTestListActivity.this).addToRequestQueue(stringRequest);
    }
}
