package com.example.pockettest.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Model.Quiz;

import java.util.List;

public class UpListAdapter extends RecyclerView.Adapter<UpListAdapter.UpViewHolder> {
    private List<Quiz> quizList;
    public UpListAdapter(List<Quiz> quizList) {
        this.quizList=quizList;
    }

    @NonNull
    @Override
    public UpListAdapter.UpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UpListAdapter.UpViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class UpViewHolder extends RecyclerView.ViewHolder{

        public UpViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
