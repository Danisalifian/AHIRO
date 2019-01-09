package com.example.dan.ahiro.model;

public class Keranjang {
    public String productname,price,image, quantity, subtotal, desciption, stock, weight ;

    public Keranjang(){

    }

    public Keranjang(String productname, String desciption, String stock, String weight, String price, String image, String quantity, String subtotal) {
        this.productname = productname;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.desciption = desciption;
        this.stock = stock;
        this.weight = weight;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }
}