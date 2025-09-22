package com.amazone.entity;
public class Product {


    public int getId() {
        return id;
    }
    public Product(){}

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Product(String name, String description, String category, float price, String ownerEmail) {

        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.ownerEmail = ownerEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private int id;
    private String name;
    private String description;
    private String category;
    private float price;



    private String ownerEmail;

}
