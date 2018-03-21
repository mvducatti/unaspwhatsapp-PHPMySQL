package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.marcos.unasp_phpmysql.Adapters.NewsAdapter;
import com.example.marcos.unasp_phpmysql.Model.News;
import com.example.marcos.unasp_phpmysql.PHP.Constants;
import com.example.marcos.unasp_phpmysql.SharedPreferences.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textviewUsername, textviewUserEmail;
    List<News> newsList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();
        loadProducts();

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        textviewUsername = findViewById(R.id.txtViewusername);
        textviewUserEmail = findViewById(R.id.txtViewUserEmail);

        textviewUsername.setText(SharedPrefManager.getInstance(this).getUser().getUsername());
        textviewUserEmail.setText(SharedPrefManager.getInstance(this).getUser().getEmail());
    }

    private void loadProducts() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_SHOW_NEWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                newsList.add(new News(
                                        product.getInt("id"),
                                        product.getString("news_post")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            NewsAdapter adapter = new NewsAdapter(MainActivity.this, newsList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
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
