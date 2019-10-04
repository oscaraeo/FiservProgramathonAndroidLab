package com.example.trainingapp.activities;

import android.os.Bundle;

import com.example.trainingapp.R;
import com.example.trainingapp.UserTableAdapter;
import com.example.trainingapp.data.User;
import com.example.trainingapp.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;

public class UserTableActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserTableAdapter userTableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_table);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupRecyclerView();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupRecyclerView() {
        List<User> users = UserDataProvider.getSingletonInstance().getUsers();
        recyclerView = findViewById(R.id.user_table_recycler_view);
        userTableAdapter = new UserTableAdapter(this, users);
        recyclerView.setAdapter(userTableAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
