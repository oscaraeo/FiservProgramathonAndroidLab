package com.example.trainingapp;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserTableAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public UserViewHolder(View itemView, UserTableAdapter adapter) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
