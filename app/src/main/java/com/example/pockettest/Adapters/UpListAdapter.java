package com.example.pockettest.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Activites.MainActivity;
import com.example.pockettest.Activites.StartTest;
import com.example.pockettest.Model.Quiz;
import com.example.pockettest.R;

import java.util.List;

public class UpListAdapter extends RecyclerView.Adapter<UpListAdapter.UpViewHolder> {
    private List<Quiz> quiz_list;
    private Context ctx;
    public UpListAdapter(List<Quiz> quiz_list, Context ctx)
    {
        this.ctx = ctx;
        this.quiz_list=quiz_list;
    }

    @NonNull
    @Override
    public UpListAdapter.UpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_list, parent, false);
        return new UpViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UpListAdapter.UpViewHolder holder, int position) {
     String upquiz_title=quiz_list.get(position).getTitle();
     String upquiz_description=quiz_list.get(position).getDescription();
     holder.uptitle.setText(upquiz_title);
     holder.updescription.setText(upquiz_description);
    }

    @Override
    public int getItemCount()
    {
        return quiz_list.size();
    }

    public class UpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Context context;
        private TextView uptitle;
        private TextView updescription;

        UpViewHolder(@NonNull View itemView) {
            super(itemView);
            context = ctx;
            uptitle=itemView.findViewById(R.id.quizTitle);
            updescription=itemView.findViewById(R.id.quizDescription);
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
