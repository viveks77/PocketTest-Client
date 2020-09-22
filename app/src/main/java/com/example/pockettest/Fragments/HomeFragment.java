package com.example.pockettest.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pockettest.Adapters.OnListAdapter;
import com.example.pockettest.Adapters.UpListAdapter;
import com.example.pockettest.DataBase.SharedPrefManager;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;
import com.example.pockettest.Util.Urls;
import com.example.pockettest.Util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private RecyclerView ongoing_rv;
    private RecyclerView upcoming_rv;
    private List<Quiz> upComingQuizList;
    private List<Quiz> onGoingQuizList;
    private OnListAdapter onListAdapter;
    private UpListAdapter upListAdapter;
    private TextView upComingTextView;
    private TextView onGoingTextView;
    private Context context;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();

        upComingTextView = view.findViewById(R.id.home_upComing_textView);
        onGoingTextView = view.findViewById(R.id.home_onGoing_textView);

        onGoingQuizList = new ArrayList<>();
        upComingQuizList = new ArrayList<>();

        ongoing_rv = view.findViewById(R.id.ongoing_rv);
        ongoing_rv.setNestedScrollingEnabled(false);
        ongoing_rv.setLayoutManager(new LinearLayoutManager(context));

        upcoming_rv=view.findViewById(R.id.upcoming_rv);
        upcoming_rv.setNestedScrollingEnabled(false);
        upcoming_rv.setLayoutManager(new LinearLayoutManager(context));

        getQuiz();
    }

    private void getQuiz(){
        onGoingQuizList.clear();
        upComingQuizList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.BASE_URL + Urls.GET_QUIZ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray parentArray = new JSONArray(response);
                    for(int i = 0; i < parentArray.length(); i++){
                        JSONObject quizObj = parentArray.getJSONObject(i);
                        Quiz quiz = new Quiz();
                        quiz.setTitle(quizObj.getString("title"));
                        quiz.setDescription(quizObj.getString("description"));
                        quiz.setTotal_marks(quizObj.getString("total_marks"));
                        quiz.setPrimary_key(quizObj.getString("pk"));
                        quiz.setPublish_date(LocalDateTime.parse(quizObj.getString("publish_date")));
                        quiz.setEnd_time(LocalDateTime.parse(quizObj.getString("end_date")));
                        LocalDateTime dateTime = LocalDateTime.now();
                        int startTimeDiff = dateTime.compareTo(quiz.getPublish_date());
                        int endTimeDiff = dateTime.compareTo(quiz.getEnd_time());
                        if(startTimeDiff > 0  && endTimeDiff < 0){
                            onGoingQuizList.add(quiz);
                        }else if(startTimeDiff <  0){
                            upComingQuizList.add(quiz);
                        }
                    }

                }catch(JSONException e){
                    Log.d("error", e.getMessage());
                }
                if(upComingQuizList.size() > 0){
                    upListAdapter = new UpListAdapter(upComingQuizList, context);
                    upcoming_rv.setAdapter(upListAdapter);
                    upListAdapter.notifyDataSetChanged();
                }else{
                    upComingTextView.setVisibility(View.VISIBLE);
                    upComingTextView.setText("No upcoming Quizes at the moment!");
                }

                if(onGoingQuizList.size() > 0){
                    onListAdapter = new OnListAdapter(onGoingQuizList,context);
                    ongoing_rv.setAdapter(onListAdapter);
                    onListAdapter.notifyDataSetChanged();
                }else{
                    onGoingTextView.setVisibility(View.VISIBLE);
                    onGoingTextView.setText("No Quizes at the moment!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Log.d("error response", error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String , String>  headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + SharedPrefManager.getInstance(context).getToken());
                return headers;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
