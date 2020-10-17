package com.example.pockettest.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

import com.example.pockettest.Model.VideoDetails;
import com.example.pockettest.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class YoutubeVideoPlayer extends AppCompatActivity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private YouTubePlayerView youTubePlayerView;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video_player);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        youTubePlayerView = findViewById(R.id.youtubeVideoPlayerId);
        getLifecycle().addObserver(youTubePlayerView);
        swipeRefreshLayout=findViewById(R.id.youtube_swipe_refresh);
        extras = getIntent().getExtras();
        final String videoId = extras.getString("videoId");


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0);
                    swipeRefreshLayout.setRefreshing(false);
                    }

                });
            }
        });


    }
}
