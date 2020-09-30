package com.example.pockettest.Activites;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pockettest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private Map<String, String> userAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        CollapsingToolbarLayout toolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        bundle = getIntent().getExtras();
        quiz = (Quiz) bundle.getSerializable("Quiz");
        toolbar.setTitle(quiz.getTitle());
        questions_list = new ArrayList<>();

        submitButton = findViewById(R.id.main_test_submit);
        marks = findViewById(R.id.main_test_marks);
        marks.setText(quiz.getTotal_marks());

        recyclerView = findViewById(R.id.main_test_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        skeleton = SkeletonLayoutUtils.applySkeleton(recyclerView, R.layout.activity_test, 3);
        skeleton.showSkeleton();
        skeleton.setShimmerDurationInMillis(1000);
        questions_list = getQuestions();
        testAdapter = new TestAdapter(questions_list);
        userAnswers = testAdapter.getAnswers();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswers();
            }
        });
    }

    private List<Questions> getQuestions(){
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
                        JSONObject questionObj = questions. getJSONObject(i);
                        Questions question = new Questions();
                        String title = (i + 1) + ". " + questionObj.getString("content");
                        question.setTitle(title);
                        question.setQuestion_id(questionObj.getString("id"));
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
                        question.setAnswer_1(answersList.get(0));
                        question.setAnswer_2(answersList.get(1));
                        question.setAnswer_3(answersList.get(2));
                        question.setAnswer_4(answersList.get(3));
                        questions_list.add(question);
                    }
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
        return questions_list;
    }

    private void submitAnswers(){
        StringRequest submitRequest = new StringRequest(Request.Method.PATCH, Urls.BASE_URL + Urls.QUIZ + quiz.getPrimary_key() + Urls.SUBMIT_QUIZ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainTest.this, "it worked", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = new JSONObject(response);
                    String message = obj.getString("message");
                    Log.d("response",message);
                }catch (JSONException e){
                    Log.d("Error response", "error ahe ithe ");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                List<Map> obj = new ArrayList<>();
                Iterator iterator = userAnswers.entrySet().iterator();
                while(iterator.hasNext()){
                    Map.Entry pair = (Map.Entry) iterator.next();
                    Map<String, String> answers = new HashMap<>();
                    answers.put("question", pair.getKey().toString());
                    answers.put("answer", pair.getValue().toString());
                    obj.add(answers);
                }
                params.put("answers", obj.toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String , String>  headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SharedPrefManager.getInstance(MainTest.this).getToken());
                return headers;
            }
        };

        VolleySingleton.getInstance(MainTest.this).addToRequestQueue(submitRequest);
    }

}
