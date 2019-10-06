package com.example.trainingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainingapp.R;
import com.example.trainingapp.data.APIClient;
import com.example.trainingapp.data.User;
import com.example.trainingapp.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
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

        downloadUserData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
                goBackAfterSave();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void downloadUserData() {
        Intent intent = getIntent();
        String userId = intent.getStringExtra("INTENT_EXTRA_USER_ID");
        if (userId == null) {
            return; // if we did not get an identifier, don't even try to download
        }
        APIClient client = UserDataProvider.getApiClient();
        Call<User> getUser = client.getUserWithID(userId);
        getUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                loadUserData();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("ViewUserActivity", "API call getUser failed, set a breakpoint here to check the throwable's message");
            }
        });
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

    private void goBackAfterSave() {
        Intent goBack = new Intent();
        goBack.putExtra("INTENT_EXTRA_EDIT_USER_RESULT", "didEdit");
        setResult(RESULT_OK, goBack);
        finish();
    }
}
