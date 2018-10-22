package com.example.dan.ahiro.model;

public class Produk {
    public String namaproduk;
    public String harga;

    public Produk(){

    }

    public Produk(String namaproduk, String harga) {
        this.namaproduk = namaproduk;
        this.harga = harga;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
