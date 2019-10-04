package com.example.trainingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainingapp.R;
import com.example.trainingapp.data.User;
import com.example.trainingapp.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class EditUserActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String userId = intent.getStringExtra("INTENT_EXTRA_USER_ID");
        user = UserDataProvider.getSingletonInstance().getUserWithID(userId);
        if (user != null) {
            loadUserData();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadUserData() {
        TextView firstName = findViewById(R.id.edit_user_first_name);
        TextView lastName = findViewById(R.id.edit_user_last_name);
        TextView email = findViewById(R.id.edit_user_email);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }
}
