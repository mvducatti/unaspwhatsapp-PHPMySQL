package com.example.marcos.unasp_phpmysql.Comprador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.marcos.unasp_phpmysql.Adapters.ProductAdapter;
import com.example.marcos.unasp_phpmysql.Model.Product;
import com.example.marcos.unasp_phpmysql.PHP.Constants;
import com.example.marcos.unasp_phpmysql.ProductDetailActivity;
import com.example.marcos.unasp_phpmysql.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetAllItems extends AppCompatActivity implements ProductAdapter.OnItemClickListener{

    //variable to hold the information that we want to pass to another activity
    public static final String EXTRA_NEWS = "newspost";
    public static final String EXTRA_PRICE = "productprice";
    public static final String EXTRA_ORIGIN = "productorigin";
    public static final String EXTRA_STATUS = "productstatus";
    public static final String EXTRA_ID = "product_id";

    ArrayList<Product> productArrayList;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recylerView);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        //Invert the view of the list
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        productArrayList = new ArrayList<>();

        loadProducts();
    }

    private void loadProducts() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.URL_SHOW_NEWS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //converting the string to json array object
                            JSONArray jsonArray = response.getJSONArray("products");

                            //traversing through all the object
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //getting product object from json array
                                JSONObject jsnews = jsonArray.getJSONObject(i);
                                //adding the product to product list
                                try {
                                    String post = jsnews.getString("product_name");
                                    int preco = jsnews.getInt("product_price");
                                    String origin = jsnews.getString("product_origin");
                                    String status = jsnews.getString("product_status");
                                    int id = jsnews.getInt("product_id");
                                    productArrayList.add(new Product(id, post, preco, origin, status));
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "quase: " + e, Toast.LENGTH_LONG).show();
                                }
                            }

                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(GetAllItems.this, productArrayList);
                            recyclerView.setAdapter(adapter);
                            /* ------ SETTING OUR ADAPTER TO OUR ONCLICKLISTERNER ---------*/
                            adapter.setOnClickListener(GetAllItems.this);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Parou aqui: " + e, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Não existe nada a venda ainda", Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(request);
    }

    @Override
    public void onItemClick(int position) {
        /* -------------- DO WHATEVER YOU WANT WITH THE CLICK EVENT HERE------------------ */
        Intent newsDetail = new Intent(this, ProductDetailActivity.class);
        Product clickedProduct = productArrayList.get(position);

        newsDetail.putExtra(EXTRA_NEWS, clickedProduct.getProduct_name());
        newsDetail.putExtra(EXTRA_PRICE, clickedProduct.getProduct_price());
        newsDetail.putExtra(EXTRA_ORIGIN, clickedProduct.getProduct_origin());
        newsDetail.putExtra(EXTRA_STATUS, clickedProduct.getProduct_status());
        newsDetail.putExtra(EXTRA_ID, clickedProduct.getProductId());

        startActivity(newsDetail);
    }

    public void atualizar (View view){

            productArrayList.clear();
            loadProducts();
    }
}
