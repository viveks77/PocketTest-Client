package com.example.pockettest.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Model.Questions;
import com.example.pockettest.R;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<Questions> questions_list;

    public TestAdapter(List<Questions> questions_list){
        this.questions_list = questions_list;

    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_test,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        Questions question = questions_list.get(position);
        holder.question.setText(question.getTitle());
        holder.answer_1.setText(question.getAnswer_1().getContent());
        holder.answer_2.setText(question.getAnswer_2().getContent());
        holder.answer_3.setText(question.getAnswer_3().getContent());
        holder.answer_4.setText(question.getAnswer_4().getContent());
    }

    @Override
    public int getItemCount() {
        return questions_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView question;
        private RadioGroup radioGroup;
        private RadioButton answer_1;
        private RadioButton answer_2;
        private RadioButton answer_3;
        private RadioButton answer_4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.test_question);
            answer_1 = itemView.findViewById(R.id.test_answer_1);
            answer_2 = itemView.findViewById(R.id.test_answer_2);
            answer_3 = itemView.findViewById(R.id.test_answer_3);
            answer_4 = itemView.findViewById(R.id.test_answer_4);

        }
    }
}
