package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.marcos.unasp_phpmysql.MainActivity.EXTRA_NEWS;
import static com.example.marcos.unasp_phpmysql.MainActivity.EXTRA_ORIGIN;
import static com.example.marcos.unasp_phpmysql.MainActivity.EXTRA_PRICE;
import static com.example.marcos.unasp_phpmysql.MainActivity.EXTRA_STATUS;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();
        String news_post = intent.getStringExtra(EXTRA_NEWS);
        String productPrice = intent.getStringExtra(EXTRA_PRICE);
        String productOrigin = intent.getStringExtra(EXTRA_ORIGIN);
        String productStatus = intent.getStringExtra(EXTRA_STATUS);

        TextView txtProductNameDetail = findViewById(R.id.txtproductnameDetail);
        TextView txtProductPriceDetail = findViewById(R.id.txtpriceDetail);
        TextView txtProductOriginDetail = findViewById(R.id.txtoriginDetail);
        TextView txtProductStatusDetail = findViewById(R.id.txtstatusDetail);

        txtProductNameDetail.setText(news_post);
        txtProductPriceDetail.setText(productPrice);
        txtProductOriginDetail.setText(productOrigin);
        txtProductStatusDetail.setText(productStatus);
    }
}
