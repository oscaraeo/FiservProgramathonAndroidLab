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
import android.widget.EditText;
import android.widget.TextView;

public class EditUserActivity extends AppCompatActivity {
    private User user;
    private EditText firstName;
    private EditText lastName;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.edit_user_first_name);
        lastName = findViewById(R.id.edit_user_last_name);
        email = findViewById(R.id.edit_user_email);
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
                saveUserData();
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadUserData() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

    private void saveUserData() {
        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setEmail(email.getText().toString());
    }
}
