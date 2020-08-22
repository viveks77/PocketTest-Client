package com.example.pockettest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;

import java.util.ArrayList;
import java.util.List;

public class QuizRecyclerViewAdapter extends RecyclerView.Adapter<QuizRecyclerViewAdapter.ViewHolder> {
    private List<Quiz> quizList;

    public QuizRecyclerViewAdapter(List<Quiz> quizList){
       this.quizList = quizList;
    }

    @NonNull
    @Override
    public QuizRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizRecyclerViewAdapter.ViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.desc.setText(quiz.getDescription());
        holder.title.setText(quiz.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return quizList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.quizTitle);
            desc = itemView.findViewById(R.id.quizDescription);
        }
    }
}
