package com.example.marcos.unasp_phpmysql.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcos.unasp_phpmysql.Model.News;
import com.example.marcos.unasp_phpmysql.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 19/03/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder> {

    private Context context;
    private ArrayList<News> newsArrayList;
    //creating a listener for the interface with the same name as our interface
    private OnItemClickListener xListener;

    //making our own custom onClickListener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnClickListener(OnItemClickListener listener){
        //used to simulate the onItemClick of a listview
        xListener = listener;
    }

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @Override
    public NewsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false);
        return new NewsAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsAdapterViewHolder holder, int position) {
        News currentNews = newsArrayList.get(position);

        //TODO pegar news poster do model
        String news_poster = currentNews.getNews_poster();
        String post = currentNews.getNews_post();
//      String poster_pic = currentNews.getPoster_profile_pic();

        byte[] decodedString = Base64.decode(currentNews.getPoster_profile_pic(), Base64.DEFAULT);
        Bitmap poster_pic = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.txtNoticia.setText(post);
        holder.txtPoster.setText(news_poster);
        holder.iv_poster.setImageBitmap(poster_pic);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class NewsAdapterViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNoticia;
        public TextView txtPoster;
        public ImageView iv_poster;

        public NewsAdapterViewHolder(View itemView) {
            super(itemView);
            txtNoticia = itemView.findViewById(R.id.txtnewsPost);
            txtPoster = itemView.findViewById(R.id.txxNewsPoster);
            iv_poster = itemView.findViewById(R.id.ivPosterPicture);

            //creating the option for when we click on something to work
            //-------------------------WARNING--------------------------//
            /*THIS IS A TRICK TO USE onItemClick AS YOU WOULD USE IN A LIST VIEW
            * IT'S BETTER TO USE HERE (ACCORDING TO THE TUTORIAL THAN USING ON THE *-onBindViewHolder-*)*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (xListener != null) {
                        int position = getAdapterPosition();
                        //noposition to make sure the position is still valid
                        if (position != RecyclerView.NO_POSITION){
                            //onItemclick is the method that we created on the interface
                            xListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
