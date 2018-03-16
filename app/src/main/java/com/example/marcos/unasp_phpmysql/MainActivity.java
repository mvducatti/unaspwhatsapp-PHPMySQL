package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.marcos.unasp_phpmysql.SharedPreferences.SharedPrefManager;

public class MainActivity extends AppCompatActivity {

    private TextView textviewUsername, textviewUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        textviewUsername = findViewById(R.id.txtViewusername);
        textviewUserEmail = findViewById(R.id.txtViewUserEmail);

        textviewUsername.setText(SharedPrefManager.getInstance(this).getUser().getUsername());
        textviewUserEmail.setText(SharedPrefManager.getInstance(this).getUser().getEmail());
    }

    public void logout(View view){
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void post(View view){
        startActivity(new Intent(this, PostNews.class));
    }
}
