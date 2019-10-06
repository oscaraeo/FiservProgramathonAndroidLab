package com.example.trainingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainingapp.R;
import com.example.trainingapp.UserTableAdapter;
import com.example.trainingapp.data.APIClient;
import com.example.trainingapp.data.User;
import com.example.trainingapp.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
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
        recyclerView = findViewById(R.id.user_table_recycler_view);
        loadRecyclerViewData();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        final UserTableActivity activity = this;
        APIClient client = UserDataProvider.getApiClient();
        Call<List<User>> getUsers = client.getAllUsers();
        getUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                userTableAdapter = new UserTableAdapter(activity, response.body());
                recyclerView.setAdapter(userTableAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("UserTableActivity", "API call getUsers failed, set a breakpoint here to check the throwable's message");
            }
        });
    }
}
