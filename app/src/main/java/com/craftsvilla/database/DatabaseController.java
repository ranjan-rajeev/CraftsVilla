package com.craftsvilla.database;

import android.content.Context;

import com.craftsvilla.model.CartEntity;
import com.craftsvilla.model.ProductEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */

public class DatabaseController {
    Context mContext;
    Realm realm;

    public DatabaseController(Context mContext) {
        this.mContext = mContext;
        realm = Realm.getDefaultInstance();
    }

    //region product master

    public List<ProductEntity> getProductList() {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities = realm.where(ProductEntity.class).findAll();
        return productEntities;
    }

    public List<ProductEntity> getProductList(int prodType) {
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities = realm.where(ProductEntity.class).equalTo("prodType", prodType).findAll();
        return productEntities;
    }

    public ProductEntity getProductDetails(int prodId) {
        ProductEntity productEntity = null;
        productEntity = realm.where(ProductEntity.class).equalTo("prodId", prodId).findFirst();
        return productEntity;
    }

    public void setProductList(List<ProductEntity> productEntities) {
        new AsyncProductMaster(mContext, productEntities).execute();
    }
    //endregion

    //region cart master
    public CartEntity getCart() {
        CartEntity cartEntity;
        cartEntity = realm.where(CartEntity.class).findFirst();
        return cartEntity;
    }

    public CartEntity addToCart(int prodId) {
        CartEntity cartEntity = getCart();

        RealmList<ProductEntity> productEntities;
        if (cartEntity != null) {

            productEntities = cartEntity.getProductEntities();

            if (!checkIfProdAlreadyExhist(productEntities, prodId)) {
                productEntities.add(getProductDetails(prodId));
                cartEntity.setProductEntities(productEntities);
                cartEntity = calculateTotalCart(productEntities);
            } else {
                return null;
            }

        } else {
            // when cart is empty
            cartEntity = new CartEntity();
            productEntities = new RealmList<>();
            productEntities.add(getProductDetails(prodId));
            cartEntity.setProductEntities(productEntities);
            cartEntity = calculateTotalCart(productEntities);
        }
        updateCart(cartEntity);
        //new AsyncCartMaster(mContext, newCartEntity).execute();
        return cartEntity;
    }

    public boolean checkIfProdAlreadyExhist(RealmList<ProductEntity> productEntities, int prodId) {
        boolean exhist = false;
        for (ProductEntity productEntity : productEntities) {
            if (productEntity.getProdId() == prodId)
                return true;
        }
        return exhist;
    }

    public int checkIfProdExhist(RealmList<ProductEntity> productEntities, int prodId) {
        int position = 0;
        for (int i = 0; i < productEntities.size(); i++) {
            if (productEntities.get(i).getProdId() == prodId) {
                return i;
            }
        }
        return position;
    }

    public CartEntity addToCart(int prodId, int quantity) {
        CartEntity cartEntity = realm.where(CartEntity.class).findFirst();
        List<ProductEntity> productEntities = cartEntity.getProductEntities();
        realm.where(ProductEntity.class).equalTo("prodId", prodId).findAll();
        return cartEntity;
    }

    public CartEntity removeFromCart(int prodId, int quantity) {
        CartEntity cartEntity = realm.where(CartEntity.class).findFirst();

        ProductEntity productEntity = realm.where(ProductEntity.class).equalTo("prodId", prodId).findFirst();
        return cartEntity;
    }

    public void updateCart(CartEntity cartEntity) {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(cartEntity);
        realm.commitTransaction();
        if (realm != null) {
            realm.close();
        }
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
        cartEntity.setCartId(10);
        return cartEntity;
    }
    //endregion

}
