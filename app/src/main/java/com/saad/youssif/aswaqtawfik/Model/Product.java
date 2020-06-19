package com.saad.youssif.aswaqtawfik.Model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saad.youssif.aswaqtawfik.View.CartView;

public class Product {

    @SerializedName("p_id")
    @Expose
    private String pId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("photo")
    @Expose
    private String photo;
    private String quantity;
    private int total;
    CartView cartView;
    Context context;

    public Product(Context context, CartView cartView) {
        this.cartView = cartView;
        this.context=context;
    }

    public Product() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
