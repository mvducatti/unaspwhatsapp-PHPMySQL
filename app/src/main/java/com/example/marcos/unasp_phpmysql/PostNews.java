package com.example.marcos.unasp_phpmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.marcos.unasp_phpmysql.Model.User;
import com.example.marcos.unasp_phpmysql.PHP.Constants;
import com.example.marcos.unasp_phpmysql.PHP.RequestHandler;
import com.example.marcos.unasp_phpmysql.SharedPreferences.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostNews extends AppCompatActivity {

    private Button btnpostar;
    private EditText editTextNewsPost;
    private ProgressBar progressBar;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);

        editTextNewsPost = findViewById(R.id.EditTextNewsPost);
        btnpostar = findViewById(R.id.btnPostar);
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);


    }

    public void salvarNoticia(View view) {

        final User user = sharedPrefManager.getInstance(this).getUser();
        final String post = editTextNewsPost.getText().toString();

        if (!post.equals("")) {

            progressBar.setVisibility(View.VISIBLE);

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER_NEWS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            try {


                                JSONObject jsonObject = new JSONObject(response);

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),
                                        Toast.LENGTH_LONG).show();



                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Erro: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = null;
                        params = new HashMap<>();
                        params.put("news_post", post);
                        params.put("user_FK", String.valueOf(user.getId()));
                        return params;
                }
            };

            RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

        } else {
            Toast.makeText(getApplicationContext(), "Por favor, não poste nada em branco", Toast.LENGTH_LONG).show();
        }

    }
}
