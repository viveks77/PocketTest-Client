package com.example.pockettest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Model.QuestionAns;
import com.example.pockettest.R;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private ArrayList<QuestionAns> queans_list;
    public TestAdapter(ArrayList<QuestionAns> queans_list){
        this.queans_list=queans_list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
         private TextView question;
         private RadioGroup radioGroup;
         private RadioButton radio1;
        private RadioButton radio2;
        private RadioButton radio3;
        private RadioButton radio4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.Question);
            radioGroup=itemView.findViewById(R.id.radio_group);
            radio1=itemView.findViewById(R.id.radio_button1);
            radio2=itemView.findViewById(R.id.radio_button2);
            radio3=itemView.findViewById(R.id.radio_button3);
            radio4=itemView.findViewById(R.id.radio_button4);

        }
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iview = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_test,parent,false);
        return new ViewHolder(iview);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
       String question=queans_list.get(position).getQuestion();
       holder.question.setText(question);
       String option1 =queans_list.get(position).getOption1();
       holder.radio1.setText(option1);
        String option2 =queans_list.get(position).getOption1();
        holder.radio2.setText(option2);
        String option3 =queans_list.get(position).getOption1();
        holder.radio3.setText(option3);
        String option4 =queans_list.get(position).getOption1();
        holder.radio4.setText(option4);

    }

    @Override
    public int getItemCount() {
        return queans_list.size();
    }
}
