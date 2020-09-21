package com.example.pockettest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Model.Quiz;
import com.example.pockettest.Model.User;
import com.example.pockettest.R;

import java.util.ArrayList;
import java.util.List;

public class OnListAdapter extends RecyclerView.Adapter<OnListAdapter.OnViewHolder> {
    private List<Quiz> quiz_list;

    public OnListAdapter(List<Quiz> quiz_list) {
        this.quiz_list=quiz_list;
    }

    @NonNull
    @Override
    public OnListAdapter.OnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list, parent, false);
        return new OnViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OnListAdapter.OnViewHolder holder, int position) {
        String quiz_title=quiz_list.get(position).getTitle();
        String quiz_description=quiz_list.get(position).getDescription();
        holder.title.setText(quiz_title);
        holder.desc.setText(quiz_description);

    }

    @Override
    public int getItemCount() {
        return quiz_list.size();
    }
    public class OnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView desc;

        OnViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.quizTitle);
            desc=itemView.findViewById(R.id.quizDescription);
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
