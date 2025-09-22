package com.amazone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Inventory  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    public Date getInventoryDate() {
        return inventoryDate;
    }

    public void setInventoryDate(Date inventoryDate) {
        this.inventoryDate = inventoryDate;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inventory(Date inventoryDate, int salesCount, float totalPrice) {
        this.inventoryDate = inventoryDate;
        this.salesCount = salesCount;
        this.totalPrice = totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    private Date inventoryDate;
    private int salesCount;
    private float totalPrice;

}
