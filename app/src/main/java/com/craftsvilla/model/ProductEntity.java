package com.craftsvilla.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rajeev Ranjan on 23-Jun-18.
 */

public class ProductEntity {
    int prodId;
    int prodType;
    int prodDiscountedPrice;
    int prodActualPrice;
    int prodDiscPercentage;
    List<String> prodImages;
    String prodDesc;
    List<String> deliveryDetails;
    int prodShippingCharge;
    HashMap<String, String> productDetails;
    String prodSellerName;

    public ProductEntity() {

        this.prodId = 1;
        this.prodType = 1;
        this.prodDiscountedPrice = 100;
        this.prodActualPrice = 200;
        this.prodDiscPercentage = 50;
        this.prodImages = new ArrayList<>();
        this.prodImages.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg");
        this.prodImages.add("https://img4.craftsvilla.com/image/upload/f_auto,t_216x216_2/C/V/CV-35971-MCRAF69422419490-1525952824-Craftsvilla_1.jpg");

        this.prodDesc = "Craftsvilla Multicolor Cotton Silk Patola Traditional Saree With Unstiched Blouse Material.";
        this.deliveryDetails = new ArrayList<>();
        this.deliveryDetails.add("Available for Cash On Delivery");
        this.deliveryDetails.add("Estimated Delivery 6-8 working days");
        this.deliveryDetails.add("7 days Free Return");
        this.deliveryDetails.add("Rs 99 Shipping Charges");
        this.prodShippingCharge = 99;
        this.productDetails = new HashMap<>();
        this.productDetails.put("Has Blouse Peice", "Yes");
        this.productDetails.put("Saree Color", "Multicolor");
        this.productDetails.put("Wash Care", "Dry Clean");
        this.productDetails.put("Pattern ", "Traditional Prints");
        this.productDetails.put("Has Blouse Peice", "Yes");
        this.productDetails.put("Saree Color", "Multicolor");
        this.productDetails.put("Wash Care", "Dry Clean");
        this.productDetails.put("Pattern ", "Traditional Prints");
        this.prodSellerName = "CraftsVilla";
        this.prodUniqueCode = "MCRAH876876767";
    }

    public ProductEntity(int prodId, int prodType, int prodDiscountedPrice, int prodActualPrice, int prodDiscPercentage, List<String> prodImages, String prodDesc, List<String> deliveryDetails, int prodShippingCharge, HashMap<String, String> productDetails, String prodSellerName, String prodUniqueCode) {
        this.prodId = prodId;
        this.prodType = prodType;
        this.prodDiscountedPrice = prodDiscountedPrice;
        this.prodActualPrice = prodActualPrice;
        this.prodDiscPercentage = prodDiscPercentage;
        this.prodImages = prodImages;
        this.prodDesc = prodDesc;
        this.deliveryDetails = deliveryDetails;
        this.prodShippingCharge = prodShippingCharge;
        this.productDetails = productDetails;
        this.prodSellerName = prodSellerName;
        this.prodUniqueCode = prodUniqueCode;
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

    public List<String> getProdImages() {
        return prodImages;
    }

    public void setProdImages(List<String> prodImages) {
        this.prodImages = prodImages;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public List<String> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(List<String> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }

    public int getProdShippingCharge() {
        return prodShippingCharge;
    }

    public void setProdShippingCharge(int prodShippingCharge) {
        this.prodShippingCharge = prodShippingCharge;
    }

    public HashMap<String, String> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(HashMap<String, String> productDetails) {
        this.productDetails = productDetails;
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
}
