package com.amazone.entity;

import java.util.Date;

public class Orders {
    public int getId() {
        return id;
    }

    public Orders(int productId, float price, String buyerEmail, Date orderDate) {
        this.productId = productId;
        this.price = price;
        this.buyerEmail = buyerEmail;
        this.orderDate = orderDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int id;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    private int productId;
    private float price;
    private String buyerEmail;
    private Date orderDate;
}
