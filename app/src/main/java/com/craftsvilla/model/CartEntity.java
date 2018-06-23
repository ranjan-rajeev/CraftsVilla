package com.craftsvilla.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */

public class CartEntity extends RealmObject implements Parcelable {
    @PrimaryKey
    int cartId;

    RealmList<ProductEntity> productEntities;
    int totalItem;
    int totalAmount;
    int subAmount;
    int shipping;

    public RealmList<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(RealmList<ProductEntity> productEntities) {
        this.productEntities = new RealmList<>();
        this.productEntities.addAll(productEntities);
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getTotalItem() {

        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(int subAmount) {
        this.subAmount = subAmount;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.productEntities);
        dest.writeInt(this.totalItem);
        dest.writeInt(this.totalAmount);
        dest.writeInt(this.subAmount);
        dest.writeInt(this.shipping);
        dest.writeInt(this.cartId);
    }

    public CartEntity() {
        this.cartId = 10;
    }

    protected CartEntity(Parcel in) {
        this.productEntities = new RealmList<>();
        this.productEntities.addAll(in.createTypedArrayList(ProductEntity.CREATOR));
        //this.productEntities = in.createTypedArrayList(ProductEntity.CREATOR);
        this.totalItem = in.readInt();
        this.totalAmount = in.readInt();
        this.subAmount = in.readInt();
        this.shipping = in.readInt();
        this.cartId = in.readInt();
    }

    public static final Parcelable.Creator<CartEntity> CREATOR = new Parcelable.Creator<CartEntity>() {
        @Override
        public CartEntity createFromParcel(Parcel source) {
            return new CartEntity(source);
        }

        @Override
        public CartEntity[] newArray(int size) {
            return new CartEntity[size];
        }
    };

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
