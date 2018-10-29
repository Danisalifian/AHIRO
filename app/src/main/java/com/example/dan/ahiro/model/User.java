package com.example.dan.ahiro.model;

public class User {
    public String nik, nama, alamat, area, email, password, telepon, gender;

    public User(){

    }

    public User(String nik, String nama, String alamat, String area, String email, String password, String telepon, String gender) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.area = area;
        this.email = email;
        this.password = password;
        this.telepon = telepon;
        this.gender = gender;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
