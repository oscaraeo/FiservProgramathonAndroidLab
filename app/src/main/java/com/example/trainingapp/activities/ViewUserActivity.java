package com.example.trainingapp.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.trainingapp.R;
import com.example.trainingapp.data.User;
import com.example.trainingapp.data.UserDataProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewUserActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("ViewUserActivity", "Scrolling Activity was just created");
        user = UserDataProvider.getSingletonInstance().getUsers().get(0);
        loadUserData();

        final ViewUserActivity activity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToEditUser = new Intent(activity, EditUserActivity.class);
                goToEditUser.putExtra("INTENT_EXTRA_USER_ID", user.getId());
                startActivity(goToEditUser);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadUserData() {
        TextView firstName = findViewById(R.id.view_user_first_name);
        TextView lastName = findViewById(R.id.view_user_last_name);
        TextView email = findViewById(R.id.view_user_email);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }
}
