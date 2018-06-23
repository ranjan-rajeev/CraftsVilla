package com.craftsvilla.dashboard;

import android.content.Intent;
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
import com.craftsvilla.productdetails.ProductDetailsActivity;

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
        LinearLayout llTop;
        //TextView tvDiscountedPrice, tvActualPrice, tvPercentOff;


        public DashboardItemHolder(View view) {
            super(view);
            ivProdImg = (ImageView) view.findViewById(R.id.ivProdImg);
            llTop = (LinearLayout) view.findViewById(R.id.llTop);
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
            final ProductEntity productEntity = productEntities.get(position);

            Glide.with(mContext.getActivity()).load(productEntity.getFeaturedImage())
                    .centerCrop()
                    .placeholder(R.drawable.ic_action_cart).into(((DashboardItemHolder) holder).ivProdImg);
            ((DashboardItemHolder) holder).llTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext.getActivity(), ProductDetailsActivity.class).putExtra("PRODUCT_ENTITY", productEntity));
                }
            });
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