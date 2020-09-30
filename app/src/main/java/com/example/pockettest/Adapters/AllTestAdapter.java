package com.example.pockettest.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllTestAdapter extends RecyclerView.Adapter<AllTestAdapter.AllViewHolder> {
    @NonNull
    @Override
    public AllTestAdapter.AllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AllTestAdapter.AllViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class AllViewHolder extends RecyclerView.ViewHolder {

        public AllViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
