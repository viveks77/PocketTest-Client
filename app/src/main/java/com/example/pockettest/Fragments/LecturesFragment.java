package com.example.pockettest.Fragments;

import android.app.ProgressDialog;
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
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.pockettest.Adapters.LectureRecyclerViewAdapter;
import com.example.pockettest.Model.VideoDetails;
import com.example.pockettest.R;
import com.example.pockettest.Util.Constants;
import com.example.pockettest.Util.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LecturesFragment extends Fragment {
    private Context context;
    private Toolbar toolbar;
    private List<VideoDetails> videoList;
    private RecyclerView recyclerView;
    private LectureRecyclerViewAdapter lectureRecyclerViewAdapter;

    public LecturesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lectures, container, false);
        context = view.getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.lectures_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        videoList = new ArrayList<>();
        fetchVideoList();
    }

    private void fetchVideoList(){
        final ProgressDialog dialog = ProgressDialog.show(context, null, "Please Wait");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.YOUTUBE_API_URL + Constants.YOUTUBE_API_KEY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try{
                    JSONObject obj = new JSONObject(response);
                    JSONArray items = obj.getJSONArray("items");

                    for(int i =0 ;i < items.length(); i++){
                        JSONObject parentObj = items.getJSONObject(i);
                        JSONObject parentId = parentObj.getJSONObject("id");
                        String videoId = parentId.getString("videoId");
                        JSONObject snippet = parentObj.getJSONObject("snippet");
                        String title = snippet.getString("title");
                        String description = snippet.getString("description");
                        JSONObject parentThumbnailsObj = snippet.getJSONObject("thumbnails");
                        JSONObject highThumbnailObj = parentThumbnailsObj.getJSONObject("high");
                        String thumbnailUrl = highThumbnailObj.getString("url");

                        VideoDetails videoDetails = new VideoDetails();
                        videoDetails.setVideoId(videoId);
                        videoDetails.setTitle(title);
                        videoDetails.setDesc(description);
                        videoDetails.setThumbnailURL(thumbnailUrl);
                        videoList.add(videoDetails);
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }

                lectureRecyclerViewAdapter = new LectureRecyclerViewAdapter(videoList, context);
                recyclerView.setAdapter(lectureRecyclerViewAdapter);
                lectureRecyclerViewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", error.getMessage());
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }
}
