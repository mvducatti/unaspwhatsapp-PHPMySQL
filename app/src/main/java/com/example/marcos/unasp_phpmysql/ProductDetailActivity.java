package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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

import static com.example.marcos.unasp_phpmysql.Comprador.GetAllItems.EXTRA_ID;
import static com.example.marcos.unasp_phpmysql.Comprador.GetAllItems.EXTRA_NEWS;
import static com.example.marcos.unasp_phpmysql.Comprador.GetAllItems.EXTRA_ORIGIN;
import static com.example.marcos.unasp_phpmysql.Comprador.GetAllItems.EXTRA_PRICE;
import static com.example.marcos.unasp_phpmysql.Comprador.GetAllItems.EXTRA_STATUS;

public class ProductDetailActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
        String news_post = intent.getStringExtra(EXTRA_NEWS);
        int productPrice = intent.getIntExtra(EXTRA_PRICE, 1);
        productId = intent.getIntExtra(EXTRA_ID, 1);
        String productOrigin = intent.getStringExtra(EXTRA_ORIGIN);
        String productStatus = intent.getStringExtra(EXTRA_STATUS);

        TextView txtProductNameDetail = findViewById(R.id.txtproductnameDetail);
        TextView txtProductPriceDetail = findViewById(R.id.txtpriceDetail);
        TextView txtProductOriginDetail = findViewById(R.id.txtoriginDetail);
        TextView txtProductStatusDetail = findViewById(R.id.txtstatusDetail);

        txtProductNameDetail.setText(news_post);
        txtProductPriceDetail.setText("R$ " + Integer.toString(productPrice) + ",00");
        txtProductOriginDetail.setText(productOrigin);
        txtProductStatusDetail.setText(productStatus);
    }

    public void ReserverProduto (View view) {

        final User user = sharedPrefManager.getInstance(this).getUser();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_RESERVE_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")){

                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"),
                                        Toast.LENGTH_LONG).show();


                            }else{
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Item Reservado com Sucesso", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Erro: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = null;
                params = new HashMap<>();
                params.put("buyer", String.valueOf(user.getId()));
                params.put("product_id", String.valueOf(productId));
                return params;
            }
        };

        RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    }
