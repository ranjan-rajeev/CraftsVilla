package com.craftsvilla.dashboard;

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
import com.craftsvilla.productdetails.ProductDetailsActivity;

import java.util.List;


public class DashboardJustLaunchedtemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Fragment mContext;
    List<ProductEntity> productEntities;


    public DashboardJustLaunchedtemAdapter(Fragment context, List<ProductEntity> list) {
        mContext = context;
        productEntities = list;
    }

    public class DashboardItemHolder extends RecyclerView.ViewHolder {
        ImageView ivProdImg;
        TextView tvDiscountedPrice, tvActualPrice, tvPercentOff;
        LinearLayout llTop;

        public DashboardItemHolder(View view) {
            super(view);
            ivProdImg = (ImageView) view.findViewById(R.id.ivProdImg);
            tvDiscountedPrice = (TextView) view.findViewById(R.id.tvDiscountedPrice);
            tvActualPrice = (TextView) view.findViewById(R.id.tvActualPrice);
            tvPercentOff = (TextView) view.findViewById(R.id.tvPercentOff);
            llTop = (LinearLayout) view.findViewById(R.id.llTop);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_just_launched, parent, false);
        return new DashboardItemHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DashboardItemHolder) {
            final ProductEntity productEntity = productEntities.get(position);

            Glide.with(mContext.getActivity()).load(productEntity.getFeaturedImage())
                    .centerCrop()
                    .placeholder(R.drawable.ic_action_cart).into(((DashboardItemHolder) holder).ivProdImg);
            ((DashboardItemHolder) holder).tvDiscountedPrice.setText("\u20B9 " + productEntity.getProdDiscountedPrice());
            ((DashboardItemHolder) holder).tvActualPrice.setPaintFlags(((DashboardItemHolder) holder).tvActualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            ((DashboardItemHolder) holder).tvActualPrice.setText("" + productEntity.getProdActualPrice());
            ((DashboardItemHolder) holder).tvPercentOff.setText("" + productEntity.getProdDiscPercentage() + "% OFF");

            ((DashboardItemHolder) holder).llTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext.getActivity(), ProductDetailsActivity.class).putExtra("PRODUCT_ENTITY", productEntity));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return productEntities.size();
    }


}