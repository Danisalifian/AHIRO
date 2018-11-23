package com.example.dan.ahiro.model;

public class Keranjang {
    public String namaprodukk;
    public String hargak;
    public String jumlahk,deskripsik,beratk;

    public Keranjang (String namaprodukk,String deskripsik ,String beratk,String hargak ,String jumlahk){
        this.namaprodukk = namaprodukk ;
        this.hargak = hargak ;
        this.jumlahk = jumlahk ;
        this.deskripsik = deskripsik;
        this.beratk = beratk;
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

    public String getDeskripsik() {
        return deskripsik;
    }

    public void setDeskripsik(String deskripsik) {
        this.deskripsik = deskripsik;
    }

    public String getBeratk() {
        return beratk;
    }

    public void setBeratk(String beratk) {
        this.beratk = beratk;
    }

}
