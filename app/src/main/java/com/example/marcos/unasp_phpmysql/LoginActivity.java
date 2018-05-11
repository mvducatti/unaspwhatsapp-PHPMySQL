package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.marcos.unasp_phpmysql.PHP.Constants;
import com.example.marcos.unasp_phpmysql.PHP.RequestHandler;
import com.example.marcos.unasp_phpmysql.SharedPreferences.SharedPrefManager;
import com.example.marcos.unasp_phpmysql.Model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextLoginEmail, editTextLoginPassword;
    private TextView txtViewRegister;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        editTextLoginEmail = findViewById(R.id.editTextLoginEmail);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        txtViewRegister = findViewById(R.id.txtViewRegister);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

    }

    public void userLogin(View view){
        final String email = editTextLoginEmail.getText().toString().trim();
        final String password = editTextLoginPassword.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")){

                                User user = new User(obj.getInt("user_id"), obj.getString("email"),
                                        obj.getString("name"), obj.getString("photo")
                                );

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();

                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                            }else{
                                Snackbar.make(findViewById(android.R.id.content),obj.getString("message"),Snackbar.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                                e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void gotoRegister(View view){
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);
    }
}
