package com.craftsvilla.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */

public class ProductDetailsEntity extends RealmObject implements Parcelable {
    String type;
    String value;

    public ProductDetailsEntity() {
        this.type = "TYpe Name";
        this.value = "value";
    }

    public ProductDetailsEntity(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.value);
    }

    protected ProductDetailsEntity(Parcel in) {
        this.type = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<ProductDetailsEntity> CREATOR = new Parcelable.Creator<ProductDetailsEntity>() {
        @Override
        public ProductDetailsEntity createFromParcel(Parcel source) {
            return new ProductDetailsEntity(source);
        }

        @Override
        public ProductDetailsEntity[] newArray(int size) {
            return new ProductDetailsEntity[size];
        }
    };
}
