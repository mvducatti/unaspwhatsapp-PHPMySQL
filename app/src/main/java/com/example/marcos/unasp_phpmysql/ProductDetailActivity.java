package com.example.marcos.unasp_phpmysql;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.marcos.unasp_phpmysql.MainActivity.EXTRA_NEWS;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Intent intent = getIntent();
        String news_post = intent.getStringExtra(EXTRA_NEWS);

        TextView txtNewsHolder = findViewById(R.id.newsPostPlaceholder);

        txtNewsHolder.setText(news_post);
    }
}
