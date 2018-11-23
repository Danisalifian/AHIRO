package com.example.dan.ahiro.model;

public class BelumLunas {
    public String namaprodukb,hargab,jumlahb,deskripsib,beratb;

    public BelumLunas(String namaprodukb, String hargab, String jumlahb,String deskripsib, String beratb){
        this.namaprodukb = namaprodukb;
        this.hargab = hargab;
        this.jumlahb = jumlahb;
        this.deskripsib = deskripsib;
        this.beratb = beratb ;
    }

    public String getNamaprodukb() {
        return namaprodukb;
    }

    public void setNamaprodukb(String namaprodukb) {
        this.namaprodukb = namaprodukb;
    }

    public String getHargab() {
        return hargab;
    }

    public void setHargab(String hargab) {
        this.hargab = hargab;
    }

    public String getJumlahb() {
        return jumlahb;
    }

    public void setJumlahb(String jumlahb) {
        this.jumlahb = jumlahb;
    }

    public String getDeskripsib() {
        return deskripsib;
    }

    public void setDeskripsib(String deskripsib) {
        this.deskripsib = deskripsib;
    }

    public String getBeratb() {
        return beratb;
    }

    public void setBeratb(String beratb) {
        this.beratb = beratb;
    }
}
