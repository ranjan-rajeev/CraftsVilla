package com.craftsvilla.database;

import android.content.Context;
import android.os.AsyncTask;

import com.craftsvilla.cart.CartActivity;
import com.craftsvilla.model.CartEntity;
import com.craftsvilla.model.ProductEntity;
import com.craftsvilla.productdetails.ProductDetailsActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */
public class AsyncCartMaster extends AsyncTask<Void, Void, Void> {
    Context mContext;
    int prodId, prodQuantity;
    CartEntity cartEntity;
    ProductEntity productEntity;

    public AsyncCartMaster(Context context, int prodId, int prodQuantity) {
       /* this.cartEntity = cartEntity;*/
        this.prodId = prodId;
        mContext = context;
        this.prodQuantity = prodQuantity;
    }


    @Override
    protected Void doInBackground(Void... voids) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    cartEntity = realm.where(CartEntity.class).findFirst();
                    productEntity = realm.where(ProductEntity.class).equalTo("prodId", prodId).findFirst();
                    productEntity.setProdQuantity(prodQuantity);
                    cartEntity = updateCartEntity(cartEntity);
                    realm.copyToRealmOrUpdate(cartEntity);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
        return null;
    }

    private CartEntity updateCartEntity(CartEntity cartEntity) {
        RealmList<ProductEntity> productEntities;
        if (cartEntity == null) {
            productEntities = new RealmList<>();
            productEntities.add(productEntity);
            //  cartEntity.setProductEntities(productEntities);
        } else {
            productEntities = cartEntity.getProductEntities();
            if (!checkIfProdAlreadyExhist(productEntities, productEntity.getProdId())) {
                productEntities.add(productEntity);
            } else {
                if (productEntity.getProdQuantity() != 0) {
                    productEntities.set(getProductIndex(productEntities, productEntity.getProdId()), productEntity);
                } else {
                    productEntities.remove(getProductIndex(productEntities, productEntity.getProdId()));
                }

            }
            //cartEntity.setProductEntities(productEntities);
        }
        cartEntity = calculateTotalCart(productEntities);
        return cartEntity;
    }

    public boolean checkIfProdAlreadyExhist(List<ProductEntity> productEntities, int prodId) {
        boolean exhist = false;
        for (ProductEntity productEntity : productEntities) {
            if (productEntity.getProdId() == prodId)
                return true;
        }

        return exhist;
    }

    public int getProductIndex(List<ProductEntity> productEntities, int prodId) {
        int exhist = 0;
        for (int i = 0; i < productEntities.size(); i++) {
            if (productEntities.get(i).getProdId() == prodId)
                return i;
        }
        return exhist;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    public CartEntity calculateTotalCart(RealmList<ProductEntity> productEntities) {
        CartEntity cartEntity = new CartEntity();
        int totalItem = 0;
        int totalAmount = 0;
        int subAmount = 0;
        int shipping = 0;
        for (ProductEntity productEntity : productEntities) {
            totalItem = totalItem + productEntity.getProdQuantity();
            subAmount = subAmount + (productEntity.getProdDiscountedPrice() * productEntity.getProdQuantity());
            shipping = shipping + productEntity.getProdShippingCharge();
        }
        totalAmount = subAmount + shipping;
        cartEntity.setShipping(shipping);
        cartEntity.setTotalAmount(totalAmount);
        cartEntity.setSubAmount(subAmount);
        cartEntity.setTotalItem(totalItem);
        cartEntity.setProductEntities(productEntities);
        if (mContext instanceof CartActivity)
            ((CartActivity) mContext).refreshAdapter(cartEntity);
        else if (mContext instanceof ProductDetailsActivity)
            ((ProductDetailsActivity) mContext).refreshAdapter(cartEntity);
        return cartEntity;
    }
}
