package com.example.trainingapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trainingapp.data.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserTableAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<User> users;

    public UserTableAdapter(Context context, List<User> users) {
        inflater = LayoutInflater.from(context);
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recycler_view_user_item, parent, false);
        return new UserViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = users.get(position);
        String userFullName = user.getFirstName() + " " + user.getLastName();
        ((UserViewHolder)holder).userName.setText(userFullName);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView userName;

        private UserViewHolder(View itemView, UserTableAdapter adapter) {
            super(itemView);
            userName = itemView.findViewById(R.id.recycler_view_item_user_name);
            userName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
