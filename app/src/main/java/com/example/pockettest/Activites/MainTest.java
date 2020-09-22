package com.example.pockettest.Activites;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pockettest.Adapters.TestAdapter;
import com.example.pockettest.DataBase.SharedPrefManager;
import com.example.pockettest.Model.Answer;
import com.example.pockettest.Model.Questions;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.Util.Urls;
import com.example.pockettest.Util.VolleySingleton;
import com.faltenreich.skeletonlayout.Skeleton;
import com.faltenreich.skeletonlayout.SkeletonLayoutUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pockettest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest extends AppCompatActivity {

    private Skeleton skeleton;
    private Bundle bundle;
    private TextView marks;
    private  RecyclerView recyclerView;
    private  Button submitButton;
    private Quiz quiz;
    private List<Questions> questions_list;
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        bundle = getIntent().getExtras();
        quiz = (Quiz) bundle.getSerializable("Quiz");
        toolbar.setTitle(quiz.getTitle());
        questions_list = new ArrayList<>();

        marks = findViewById(R.id.main_test_marks);
        marks.setText(quiz.getTotal_marks());

        recyclerView = findViewById(R.id.main_test_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        skeleton = SkeletonLayoutUtils.applySkeleton(recyclerView, R.layout.activity_test, 3);
        skeleton.showSkeleton();
        skeleton.setShimmerDurationInMillis(1000);
        getQuestions();
    }

    private void getQuestions(){
        questions_list.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.BASE_URL + Urls.GET_QUIZ_DETAILS + quiz.getPrimary_key() + "/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                skeleton.showOriginal();
                try{
                    JSONObject parentObj = new JSONObject(response);
                    JSONObject quiz = parentObj.getJSONObject("quiz");
                    JSONArray questions = quiz.getJSONArray("questions");
                    for(int i = 0;  i < questions.length();  i++){
                        JSONObject questionObj = questions.getJSONObject(i);
                        Questions question = new Questions();
                        String title = (i + 1) + ". " + questionObj.getString("content");
                        question.setTitle(title);
                        question.setMarks(questionObj.getString("marks"));
                        question.setQuiz_id(questionObj.getString("quiz"));
                        JSONArray answerArrayObj = questionObj.getJSONArray("answers");

                        List<Answer> answersList = new ArrayList<>();
                        for(int j =0; j < answerArrayObj.length(); j++){
                            JSONObject answerObj = answerArrayObj.getJSONObject(j);
                            Answer answer = new Answer();
                            answer.setAnswer_id(answerObj.getString("id"));
                            answer.setContent(answerObj.getString("content"));
                            answer.setIs_correct(answerObj.getBoolean("is_correct"));
                            answer.setQuestion_id(answerObj.getString("question"));
                            answersList.add(answer);
                        }
                        question.setAnswer_1(answersList.get(1));
                        question.setAnswer_2(answersList.get(2));
                        question.setAnswer_3(answersList.get(3));
                        question.setAnswer_4(answersList.get(4));

                        questions_list.add(question);
                    }
                    testAdapter = new TestAdapter(questions_list);
                    recyclerView.setAdapter(testAdapter);
                    testAdapter.notifyDataSetChanged();
                }catch (JSONException e){
                    Log.d("error :  ",  e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error on response", error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String , String>  headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SharedPrefManager.getInstance(MainTest.this).getToken());
                return headers;
            }
        };
        VolleySingleton.getInstance(MainTest.this).addToRequestQueue(stringRequest);
    }

}
