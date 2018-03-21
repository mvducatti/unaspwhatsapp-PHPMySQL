package com.example.marcos.unasp_phpmysql.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcos.unasp_phpmysql.Model.News;
import com.example.marcos.unasp_phpmysql.R;

import java.util.List;

/**
 * Created by marcos on 19/03/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<News> newsList;

    public NewsAdapter(Context mCtx, List<News> newsList) {
        this.mCtx = mCtx;
        this.newsList = newsList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.news_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.textViewNewsPost.setText(news.getNews_post());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNewsPost;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewNewsPost = itemView.findViewById(R.id.txtnewsPost);
        }
    }

}
