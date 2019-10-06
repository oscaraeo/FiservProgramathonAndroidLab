package com.example.trainingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainingapp.DownloadImageAsyncTask;
import com.example.trainingapp.R;
import com.example.trainingapp.data.APIClient;
import com.example.trainingapp.data.User;
import com.example.trainingapp.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewUserActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("ViewUserActivity", "Scrolling Activity was just created");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ViewUserActivity activity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToEditUser = new Intent(activity, EditUserActivity.class);
                goToEditUser.putExtra("INTENT_EXTRA_USER_ID", user.getId());
                startActivityForResult(goToEditUser, 510);
            }
        });
        downloadUserData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.view_user_menu_action_delete) {
            deleteUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void downloadUserData() {
        Intent intent = getIntent();
        String userId = intent.getStringExtra("USER_ID");
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
        TextView firstName = findViewById(R.id.view_user_first_name);
        TextView lastName = findViewById(R.id.view_user_last_name);
        TextView email = findViewById(R.id.view_user_email);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        ImageView imageView = this.findViewById(R.id.view_user_avatar_imageView);
        new DownloadImageAsyncTask(imageView).execute(user.getAvatar());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            downloadUserData();
        }
    }

    private void deleteUser() {
        APIClient client = UserDataProvider.getApiClient();
        Call<User> deleteUser = client.deleteUserWithID(user.getId());
        deleteUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("ViewUserActivity", "API call deleteUser failed, set a breakpoint here to check the throwable's message");
            }
        });
    }
}
