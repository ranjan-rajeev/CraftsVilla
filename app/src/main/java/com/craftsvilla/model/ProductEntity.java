package com.craftsvilla.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.RealmCollection;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */

public class ProductEntity extends RealmObject implements Parcelable {
    @PrimaryKey
    int prodId;
    int prodType;
    int prodDiscountedPrice;
    int prodActualPrice;
    int prodDiscPercentage;
    String featuredImage;
    RealmList<String> prodImages;
    String prodDesc;
    RealmList<String> deliveryDetails;
    int prodShippingCharge;

    public int getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    RealmList<ProductDetailsEntity> productDetails;
    String prodSellerName;
    int prodQuantity;

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public ProductEntity() {

        this.prodId = 1;
        this.prodType = 1;
        this.prodDiscountedPrice = 100;

        this.prodActualPrice = 200;
        this.prodDiscPercentage = 50;
        this.prodImages = new RealmList<>();
        this.prodDesc = "Craftsvilla Multicolor Cotton Silk Patola Traditional Saree With Unstiched Blouse Material.";
        this.deliveryDetails = new RealmList<>();
        /*this.deliveryDetails.add("Available for Cash On Delivery");
        this.deliveryDetails.add("Estimated Delivery 6-8 working days");
        this.deliveryDetails.add("7 days Free Return");
        this.deliveryDetails.add("Rs 99 Shipping Charges");*/
        this.prodShippingCharge = 99;
        this.productDetails = new RealmList<>();
       /* this.productDetails.add(new ProductDetailsEntity("Has Blouse Peice", "Yes"));
        this.productDetails.add(new ProductDetailsEntity("Saree Color", "Multicolor"));
        this.productDetails.add(new ProductDetailsEntity("Wash Care", "Dry Clean"));
        this.productDetails.add(new ProductDetailsEntity("Pattern ", "Traditional Prints"));
        this.productDetails.add(new ProductDetailsEntity("Has Blouse Peice", "Yes"));
        this.productDetails.add(new ProductDetailsEntity("Saree Color", "Multicolor"));
        this.productDetails.add(new ProductDetailsEntity("Wash Care", "Dry Clean"));
        this.productDetails.add(new ProductDetailsEntity("Pattern ", "Traditional Prints"));*/
        this.prodSellerName = "CraftsVilla";
        this.prodUniqueCode = "MCRAH876876767";
    }

    public ProductEntity(int prodId, int prodType, int prodActualPrice, int prodDiscountedPrice, int prodDiscPercentage, String featuredImage) {
        this.prodId = prodId;
        this.prodType = prodType;
        this.prodDiscountedPrice = prodDiscountedPrice;
        this.prodActualPrice = prodActualPrice;
        this.prodDiscPercentage = prodDiscPercentage;
        this.featuredImage = featuredImage;
        this.prodDesc = "Craftsvilla Magenta Color Silk Saree With Butta Work And Unstitched Blouse Material";
        this.prodImages = getImagesList();
        this.productDetails = getProductDetailsList();
        this.deliveryDetails = getDeliveryDetailsList();
        this.prodSellerName = "CraftsVilla";
        this.prodUniqueCode = "MCRAH876876767";
        this.prodQuantity = 1;
        this.prodShippingCharge = 49;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getProdType() {
        return prodType;
    }

    public void setProdType(int prodType) {
        this.prodType = prodType;
    }

    public int getProdDiscountedPrice() {
        return prodDiscountedPrice;
    }

    public void setProdDiscountedPrice(int prodDiscountedPrice) {
        this.prodDiscountedPrice = prodDiscountedPrice;
    }

    public int getProdActualPrice() {
        return prodActualPrice;
    }

    public void setProdActualPrice(int prodActualPrice) {
        this.prodActualPrice = prodActualPrice;
    }

    public int getProdDiscPercentage() {
        return prodDiscPercentage;
    }

    public void setProdDiscPercentage(int prodDiscPercentage) {
        this.prodDiscPercentage = prodDiscPercentage;
    }

    public RealmList<String> getProdImages() {
        return prodImages;
    }

    public void setProdImages(RealmList<String> prodImages) {
        this.prodImages = new RealmList<>();
        this.prodImages.addAll(prodImages);
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public RealmList<String> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(RealmList<String> deliveryDetails) {
        this.deliveryDetails = new RealmList<>();
        this.deliveryDetails.addAll(deliveryDetails);
    }

    public int getProdShippingCharge() {
        return prodShippingCharge;
    }

    public void setProdShippingCharge(int prodShippingCharge) {
        this.prodShippingCharge = prodShippingCharge;
    }

    public RealmList<ProductDetailsEntity> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(RealmList<ProductDetailsEntity> productDetails) {
        this.productDetails = new RealmList<>();
        this.productDetails.addAll(productDetails);
    }

    public String getProdSellerName() {
        return prodSellerName;
    }

    public void setProdSellerName(String prodSellerName) {
        this.prodSellerName = prodSellerName;
    }

    public String getProdUniqueCode() {
        return prodUniqueCode;
    }

    public void setProdUniqueCode(String prodUniqueCode) {
        this.prodUniqueCode = prodUniqueCode;
    }

    String prodUniqueCode;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.prodId);
        dest.writeInt(this.prodType);
        dest.writeInt(this.prodDiscountedPrice);
        dest.writeInt(this.prodActualPrice);
        dest.writeInt(this.prodDiscPercentage);
        dest.writeStringList(this.prodImages);
        dest.writeString(this.prodDesc);
        dest.writeStringList(this.deliveryDetails);
        dest.writeInt(this.prodShippingCharge);
        dest.writeTypedList(this.productDetails);
        dest.writeString(this.prodSellerName);
        dest.writeString(this.prodUniqueCode);
        dest.writeString(this.featuredImage);
        dest.writeInt(this.prodQuantity);
    }

    protected ProductEntity(Parcel in) {
        this.prodId = in.readInt();
        this.prodType = in.readInt();
        this.prodDiscountedPrice = in.readInt();
        this.prodActualPrice = in.readInt();
        this.prodDiscPercentage = in.readInt();

        this.prodImages = new RealmList<>();
        this.prodImages.addAll(in.createStringArrayList());
        //this.prodImages = in.createStringArrayList();
        this.prodDesc = in.readString();
        //this.deliveryDetails = in.createStringArrayList();
        this.deliveryDetails = new RealmList<>();
        this.deliveryDetails.addAll(in.createStringArrayList());
        this.prodShippingCharge = in.readInt();
        //this.productDetails = new ArrayList<ProductDetailsEntity>();
        this.productDetails = new RealmList<>();
        this.productDetails.addAll(in.createTypedArrayList(ProductDetailsEntity.CREATOR));
        //in.readList(this.productDetails, ProductDetailsEntity.class.getClassLoader());
        this.prodSellerName = in.readString();
        this.prodUniqueCode = in.readString();
        this.featuredImage = in.readString();
        this.prodQuantity = in.readInt();
    }

    public static final Parcelable.Creator<ProductEntity> CREATOR = new Parcelable.Creator<ProductEntity>() {
        @Override
        public ProductEntity createFromParcel(Parcel source) {
            return new ProductEntity(source);
        }

        @Override
        public ProductEntity[] newArray(int size) {
            return new ProductEntity[size];
        }
    };

    private RealmList<ProductDetailsEntity> getProductDetailsList() {
        RealmList<ProductDetailsEntity> productDetailsEntities = new RealmList<>();
        productDetailsEntities.add(new ProductDetailsEntity("Has Blouse Peice", "Yes"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Color", "Multicolor"));
        productDetailsEntities.add(new ProductDetailsEntity("Wash Care", "Dry Clean"));
        productDetailsEntities.add(new ProductDetailsEntity("Blouse Peice Fabric", "Cotton Silk"));
        productDetailsEntities.add(new ProductDetailsEntity("Pattern ", "Traditional Prints"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Fabrics", "Cotton Silk"));
        productDetailsEntities.add(new ProductDetailsEntity("Style", "ReadyMAde Saree"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Work", "Patola"));
        productDetailsEntities.add(new ProductDetailsEntity("Blouse Peice Size", "0.8 Meters"));
        productDetailsEntities.add(new ProductDetailsEntity("Saree Length", "5.5 Meters"));
        productDetailsEntities.add(new ProductDetailsEntity("Occassion", "Traditional Festival"));
        return productDetailsEntities;
    }

    private RealmList<String> getDeliveryDetailsList() {
        RealmList<String> stringRealmList = new RealmList<>();
        stringRealmList.add("Available for Cash On Delivery");
        stringRealmList.add("Estimated Delivery 6-8 Working Days");
        stringRealmList.add("7 days Free return");
        stringRealmList.add("RS 99 Shipping charges");
        return stringRealmList;
    }

    private RealmList<String> getImagesList() {
        RealmList<String> stringRealmList = new RealmList<>();
        stringRealmList.add("https://img3.craftsvilla.com/image/upload/f_auto,q_auto,fl_lossy,t_500x500_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928");
        stringRealmList.add("https://img3.craftsvilla.com/image/upload/w_800,c_lfill,f_auto,q_auto,g_auto,fl_lossy,e_sharpen/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_2.jpg-1526298928");
        stringRealmList.add("https://img3.craftsvilla.com/image/upload/w_800,c_lfill,f_auto,q_auto,g_auto,fl_lossy,e_sharpen/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_3.jpg-1526298928");
        stringRealmList.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35655-MCRAF41280071060-1522062244-Craftsvilla_1.jpg");
        stringRealmList.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg-1526298928");
        return stringRealmList;
    }
}
