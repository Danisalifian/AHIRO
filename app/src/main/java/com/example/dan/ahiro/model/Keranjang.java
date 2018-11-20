package com.example.dan.ahiro.model;

public class Keranjang {
    public String namaprodukk;
    public String hargak;
    public String jumlahk;

    public Keranjang (String namaprodukk, String hargak ,String jumlahk){
        this.namaprodukk = namaprodukk ;
        this.hargak = hargak ;
        this.jumlahk = jumlahk ;
    }

    public String getNamaprodukk() {
        return namaprodukk;
    }

    public void setNamaprodukk(String namaprodukk) {
        this.namaprodukk = namaprodukk;
    }

    public String getHargak() {
        return hargak;
    }

    public void setHargak(String hargak) {
        this.hargak = hargak;
    }

    public String getJumlahk() {
        return jumlahk;
    }

    public void setJumlahk(String jumlahk) {
        this.jumlahk = jumlahk;
    }
}
