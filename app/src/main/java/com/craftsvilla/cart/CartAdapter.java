package com.craftsvilla.cart;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.craftsvilla.R;
import com.craftsvilla.database.AsyncCartMaster;
import com.craftsvilla.model.ProductEntity;

import io.realm.RealmList;


public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity mContext;
    RealmList<ProductEntity> productEntities;


    public CartAdapter(Activity context, RealmList<ProductEntity> list) {
        mContext = context;
        productEntities = list;
    }

    public class DashboardItemHolder extends RecyclerView.ViewHolder {
        ImageView ivFeatureImage, ivDelete;
        TextView prodDesc, tvAmount, tvMinus, tvCount, tvPlus;

        public DashboardItemHolder(View view) {
            super(view);
            ivFeatureImage = (ImageView) view.findViewById(R.id.ivFeatureImage);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
            prodDesc = (TextView) view.findViewById(R.id.prodDesc);
            tvAmount = (TextView) view.findViewById(R.id.tvAmount);
            tvMinus = (TextView) view.findViewById(R.id.tvMinus);
            tvCount = (TextView) view.findViewById(R.id.tvCount);
            tvPlus = (TextView) view.findViewById(R.id.tvPlus);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_cart, parent, false);
        return new DashboardItemHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof DashboardItemHolder) {
            final ProductEntity productEntity = productEntities.get(position);
            ((DashboardItemHolder) holder).tvAmount.setText("" + productEntity.getProdDiscountedPrice());
            ((DashboardItemHolder) holder).tvCount.setText("" + productEntity.getProdQuantity());
            ((DashboardItemHolder) holder).prodDesc.setText("" + productEntity.getProdDesc());

            Glide.with(mContext).load(productEntity.getProdImages().get(position))
                    .fitCenter()
                    .override(70, 100)
                    .placeholder(R.drawable.ic_action_cart).into(((DashboardItemHolder) holder).ivFeatureImage);


            ((DashboardItemHolder) holder).ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((CartActivity) mContext).deleteProduct(productEntity);
                    ((DashboardItemHolder) holder).tvCount.setText("0");
                }
            });
            ((DashboardItemHolder) holder).tvPlus.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View view) {
                    if (productEntity.getProdQuantity() <= 10) {
                        ((CartActivity) mContext).addProduct(productEntity);
                        ((DashboardItemHolder) holder).tvCount.setText("" + (productEntity.getProdQuantity() + 1));
                    }
                }
            });
            ((DashboardItemHolder) holder).tvMinus.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View view) {
                    if (productEntity.getProdQuantity() > 1) {
                        ((CartActivity) mContext).minusProduct(productEntity);
                        ((DashboardItemHolder) holder).tvCount.setText("" + (productEntity.getProdQuantity() - 1));
                    }

                }
            });
        }
    }

    private void minusProduct(ProductEntity productEntity) {
        new AsyncCartMaster(mContext, productEntity.getProdId(), (productEntity.getProdQuantity() - 1)).execute();
    }

    private void addProduct(ProductEntity productEntity) {
        new AsyncCartMaster(mContext, productEntity.getProdId(), (productEntity.getProdQuantity() + 1)).execute();
    }

    private void deleteProduct(ProductEntity productEntity) {
        new AsyncCartMaster(mContext, productEntity.getProdId(), 0).execute();
    }

    @Override
    public int getItemCount() {
        return productEntities.size();
    }


}