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

public class UpListAdapter extends RecyclerView.Adapter<UpListAdapter.UpViewHolder> {
    private List<Quiz> upquizList;
    public UpListAdapter(List<Quiz> upquizList)
    {
        this.upquizList=upquizList;
    }

    @NonNull
    @Override
    public UpListAdapter.UpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list, parent, false);
        return new UpViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpListAdapter.UpViewHolder holder, int position) {
     String upquiz_title=upquizList.get(position).getTitle();
     String upquiz_description=upquizList.get(position).getDescription();
     holder.uptitle.setText(upquiz_title);
     holder.updescription.setText(upquiz_description);
    }

    @Override
    public int getItemCount()
    {
        return upquizList.size();
    }

    public class UpViewHolder extends RecyclerView.ViewHolder{

        private TextView uptitle;
        private TextView updescription;

        UpViewHolder(@NonNull View itemView) {
            super(itemView);
            uptitle=itemView.findViewById(R.id.quizTitle);
            updescription=itemView.findViewById(R.id.quizDescription);

        }
    }
}
