package com.example.marcos.unasp_phpmysql.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcos.unasp_phpmysql.Model.Product;
import com.example.marcos.unasp_phpmysql.R;

import java.util.ArrayList;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyProductViewHolder> {

    private Context context;
    private ArrayList<Product> productArrayList;


    public MyProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @Override
    public MyProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_list, parent, false);
        return new MyProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyProductViewHolder holder, int position) {
        Product currentProduct = productArrayList.get(position);

        String post = currentProduct.getProduct_name();
        String preco = String.valueOf(currentProduct.getProduct_price());
        String origin = currentProduct.getProduct_origin();
        String status = currentProduct.getProduct_status();

        holder.myProductName.setText(post);
        holder.myProductPrice.setText(origin);
        holder.myProductOrigin.setText(preco);
        holder.myProductStatus.setText(status);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyProductViewHolder extends RecyclerView.ViewHolder {

        public TextView myProductName;
        public TextView myProductPrice;
        public TextView myProductOrigin;
        public TextView myProductStatus;

        public MyProductViewHolder(View itemView) {
            super(itemView);
            myProductName = itemView.findViewById(R.id.txtnewsPost);
            myProductPrice = itemView.findViewById(R.id.txtPreco);
            myProductOrigin = itemView.findViewById(R.id.txtOrigin);
            myProductStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}
