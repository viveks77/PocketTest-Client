package com.example.pockettest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Activites.MainTest;
import com.example.pockettest.Activites.StartTest;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;

import java.util.List;

public class OnListAdapter extends RecyclerView.Adapter<OnListAdapter.OnViewHolder> {
    private List<Quiz> quiz_list;
    private Context ctx;

    public OnListAdapter(List<Quiz> quiz_list, Context ctx) {
        this.ctx = ctx;
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

        private Context context;
        private TextView title;
        private TextView desc;

        OnViewHolder(@NonNull View itemView) {
            super(itemView);
            context = ctx;
            title=itemView.findViewById(R.id.quizTitle);
            desc=itemView.findViewById(R.id.quizDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Quiz quiz = quiz_list.get(getAdapterPosition());
            Intent intent = new Intent(context, StartTest.class);
            intent.putExtra("quiz", quiz);
            context.startActivity(intent);
        }
    }

}
