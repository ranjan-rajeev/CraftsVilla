package com.craftsvilla.productdetails;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.craftsvilla.R;
import com.craftsvilla.model.ProductEntity;

import java.util.List;

import io.realm.RealmList;


public class ProductDeliveryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mContext;
    RealmList<String> productEntities;


    public ProductDeliveryAdapter(Activity context, RealmList<String> list) {
        mContext = context;
        productEntities = list;
    }

    public class DashboardItemHolder extends RecyclerView.ViewHolder {
        ImageView ivProdImg;
        TextView tvName;

        public DashboardItemHolder(View view) {
            super(view);
            //ivProdImg = (ImageView) view.findViewById(R.id.ivProdImg);
            tvName = (TextView) view.findViewById(R.id.tvName);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_prod_delivery, parent, false);
        return new DashboardItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DashboardItemHolder) {
            ((DashboardItemHolder) holder).tvName.setText("" + productEntities.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return productEntities.size();
    }


}