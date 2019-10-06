package com.example.trainingapp;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trainingapp.activities.ViewUserActivity;
import com.example.trainingapp.data.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserTableAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<User> users;
    private Context context;

    public UserTableAdapter(Context context, List<User> users) {
        this.context = context;
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
        UserViewHolder viewHolder = (UserViewHolder)holder;
        viewHolder.userName.setText(userFullName);
        viewHolder.position = position;
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private void goToViewUserForViewHolderAtPosition(Integer position) {
        User user = users.get(position);
        Intent goToViewUser = new Intent(context, ViewUserActivity.class);
        goToViewUser.putExtra("USER_ID", user.getId());
        context.startActivity(goToViewUser);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Integer position;
        private TextView userName;
        private UserTableAdapter adapter;

        private UserViewHolder(View itemView, UserTableAdapter adapter) {
            super(itemView);
            userName = itemView.findViewById(R.id.recycler_view_item_user_name);
            userName.setOnClickListener(this);
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            adapter.goToViewUserForViewHolderAtPosition(position);
        }
    }
}
