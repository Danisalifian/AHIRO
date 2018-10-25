package com.example.dan.ahiro.model;

public class Produk {
    public String namaproduk;
    public String deskripsi;
    public String berat;
    public String harga;
    public String stok;

    public Produk(){

    }

    public Produk(String namaproduk, String deskripsi, String berat, String harga, String stok) {
        this.namaproduk = namaproduk;
        this.deskripsi = deskripsi;
        this.berat = berat;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }
}
