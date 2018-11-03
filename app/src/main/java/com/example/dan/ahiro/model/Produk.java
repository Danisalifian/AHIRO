package com.example.dan.ahiro.model;

public class Produk {
    public String productname, description, weight, price, stock, image;

    public Produk(){}

    public Produk(String productname, String description, String weight, String price, String stock, String image) {
        this.productname = productname;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
