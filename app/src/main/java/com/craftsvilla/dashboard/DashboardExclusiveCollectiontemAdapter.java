package com.craftsvilla.dashboard;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.craftsvilla.R;
import com.craftsvilla.model.ProductEntity;

import java.util.List;


public class DashboardExclusiveCollectiontemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Fragment mContext;
    List<ProductEntity> productEntities;


    public DashboardExclusiveCollectiontemAdapter(Fragment context, List<ProductEntity> list) {
        mContext = context;
        productEntities = list;
    }

    public class DashboardItemHolder extends RecyclerView.ViewHolder {
        ImageView ivProdImg;
       //TextView tvDiscountedPrice, tvActualPrice, tvPercentOff;


        public DashboardItemHolder(View view) {
            super(view);
            ivProdImg = (ImageView) view.findViewById(R.id.ivProdImg);
            /*tvDiscountedPrice = (TextView) view.findViewById(R.id.tvDiscountedPrice);
            tvActualPrice = (TextView) view.findViewById(R.id.tvActualPrice);
            tvPercentOff = (TextView) view.findViewById(R.id.tvPercentOff);*/
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_exclusive_collection, parent, false);
        return new DashboardItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DashboardItemHolder) {
            ProductEntity productEntity = productEntities.get(position);

            Glide.with(mContext.getActivity()).load(productEntity.getProdImages().get(0))
                    .fitCenter()
                    .placeholder(R.drawable.ic_action_cart).into(((DashboardItemHolder) holder).ivProdImg);
            /*((DashboardItemHolder) holder).tvDiscountedPrice.setText("" + productEntity.getProdDiscountedPrice());
            ((DashboardItemHolder) holder).tvActualPrice.setText("" + productEntity.getProdActualPrice());
            ((DashboardItemHolder) holder).tvPercentOff.setText("" + productEntity.getProdDiscPercentage());*/
        }
    }

    @Override
    public int getItemCount() {
        return productEntities.size();
    }


}