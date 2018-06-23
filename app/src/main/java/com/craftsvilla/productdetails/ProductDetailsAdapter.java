package com.craftsvilla.productdetails;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftsvilla.R;
import com.craftsvilla.model.ProductDetailsEntity;
import com.craftsvilla.model.ProductEntity;

import java.util.List;

import io.realm.RealmList;


public class ProductDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mContext;
    RealmList<ProductDetailsEntity> productEntities;


    public ProductDetailsAdapter(Activity context, RealmList<ProductDetailsEntity> list) {
        mContext = context;
        productEntities = list;
    }

    public class DashboardItemHolder extends RecyclerView.ViewHolder {
        ImageView ivProdImg;
        TextView tvName, tvValue;

        public DashboardItemHolder(View view) {
            super(view);
            //ivProdImg = (ImageView) view.findViewById(R.id.ivProdImg);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvValue = (TextView) view.findViewById(R.id.tvValue);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_prod_desc, parent, false);
        return new DashboardItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DashboardItemHolder) {
            final ProductDetailsEntity productDetailsEntity = productEntities.get(position);
            ((DashboardItemHolder) holder).tvName.setText("" + productDetailsEntity.getType());
            ((DashboardItemHolder) holder).tvValue.setText("" + productDetailsEntity.getValue());
        }
    }

    @Override
    public int getItemCount() {
        return productEntities.size();
    }


}