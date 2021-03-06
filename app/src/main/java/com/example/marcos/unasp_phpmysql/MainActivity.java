package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener{

    //variable to hold the information that we want to pass to another activity
    public static final String EXTRA_NEWS = "newspost";

    private TextView textviewUsername, textviewUserEmail;
    ArrayList<News> newsArrayList;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        //get username
        textviewUsername = findViewById(R.id.txtViewusername);
        textviewUsername.setText(SharedPrefManager.getInstance(this).getUser().getName());

        //get email
        textviewUserEmail = findViewById(R.id.txtViewUserEmail);
        textviewUserEmail.setText(SharedPrefManager.getInstance(this).getUser().getEmail());

        recyclerView = findViewById(R.id.recylerView);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        //Invert the view of the list
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        newsArrayList = new ArrayList<>();

        loadProducts();
    }

    private void loadProducts() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.URL_SHOW_NEWS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //converting the string to json array object
                            JSONArray jsonArray = response.getJSONArray("news");

                            //traversing through all the object
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //getting product object from json array
                                JSONObject jsnews = jsonArray.getJSONObject(i);
                                //adding the product to product listW
                                String post = jsnews.getString("news_post");
                                String poster_name = jsnews.getString("users.username");
                                String poster_pic = jsnews.getString("users.user_profile_pic");

                                //decoding to bitmap
                                byte[] decodedString = Base64.decode(poster_pic, Base64.DEFAULT);
                                Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                newsArrayList.add(new News(post, poster_name, bmp));
                            }

                            //creating adapter object and setting it to recyclerview
                            NewsAdapter adapter = new NewsAdapter(MainActivity.this, newsArrayList);
                            recyclerView.setAdapter(adapter);
                            /* ------ SETTING OUR ADAPTER TO OUR ONCLICKLISTERNER ---------*/
                            adapter.setOnClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            Snackbar.make(findViewById(android.R.id.content),"Algo deu errado: " + e,Snackbar.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(findViewById(android.R.id.content),"Algo deu errado: " + error + "",Snackbar.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(request);
    }


    public void logout(View view){
        SharedPrefManager.getInstance(this).logout();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void post(View view){
        startActivity(new Intent(this, PostNews.class));
    }

    @Override
    public void onItemClick(int position) {
        /* -------------- DO WHATEVER YOU WANT WITH THE CLICK EVENT HERE------------------ */
        Intent newsDetail = new Intent(this, NewsDetailActivity.class);
        News clickedNews = newsArrayList.get(position);

        newsDetail.putExtra(EXTRA_NEWS, clickedNews.getNews_post());

        startActivity(newsDetail);
    }
}
