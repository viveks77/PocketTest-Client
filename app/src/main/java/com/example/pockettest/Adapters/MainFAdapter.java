package com.example.pockettest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pockettest.Model.Subjects;
import com.example.pockettest.R;

import java.util.List;

public class MainFAdapter extends RecyclerView.Adapter<MainFAdapter.MainviewHolder> {
   private Context ctx;
   private List<Subjects> subjectsList;

    public MainFAdapter(Context ctx, List<Subjects> subjectsList) {
        this.ctx = ctx;
        this.subjectsList = subjectsList;
    }

    @NonNull
    @Override
    public MainviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater= LayoutInflater.from(ctx);
        view=layoutInflater.inflate(R.layout.fragment_main_items,parent,false);
        return new MainviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainviewHolder holder, int position) {
       holder.subject_name.setText(subjectsList.get(position).getSubjects());
       holder.subject_thumbnail.setImageResource(subjectsList.get(position).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return subjectsList.size();
    }

    public static class MainviewHolder extends RecyclerView.ViewHolder {
        TextView subject_name;
        ImageView subject_thumbnail;
        CardView mainf_cardview;
        public MainviewHolder(@NonNull View itemView) {
            super(itemView);
            subject_name=(TextView) itemView.findViewById(R.id.subject_title);
            subject_thumbnail=(ImageView) itemView.findViewById(R.id.subject_image);
            mainf_cardview=(CardView) itemView.findViewById(R.id.mainf_cardview);

        }
    }
}
