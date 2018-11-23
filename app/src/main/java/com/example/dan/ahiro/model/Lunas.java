package com.example.dan.ahiro.model;

public class Lunas {
    public String namaprodukl,hargal,jumlahl,deskripsil,beratl;

    public Lunas(String namaprodukl, String hargal, String jumlahl, String deskripsil, String beratl){
        this.namaprodukl = namaprodukl;
        this.hargal = hargal;
        this.jumlahl = jumlahl;
        this.deskripsil = deskripsil;
        this.beratl = beratl;
    }

    public String getNamaprodukl() {
        return namaprodukl;
    }

    public void setNamaprodukl(String namaprodukl) {
        this.namaprodukl = namaprodukl;
    }

    public String getHargal() {
        return hargal;
    }

    public void setHargal(String hargal) {
        this.hargal = hargal;
    }

    public String getJumlahl() {
        return jumlahl;
    }

    public void setJumlahl(String jumlahl) {
        this.jumlahl = jumlahl;
    }

    public String getDeskripsil() {
        return deskripsil;
    }

    public void setDeskripsil(String deskripsil) {
        this.deskripsil = deskripsil;
    }

    public String getBeratl() {
        return beratl;
    }

    public void setBeratl(String beratl) {
        this.beratl = beratl;
    }
}
