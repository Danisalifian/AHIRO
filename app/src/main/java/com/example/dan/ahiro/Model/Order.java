package com.example.dan.ahiro.Model;

public class Order {

    public String information, productfee, shipaddress, shipmentfee, status, totalpayment, recipient, phone;
    public long timestamp;

    public Order(){}

    public Order(String information, String productfee, String shipaddress, String shipmentfee, String status, String totalpayment, String recipient, String phone, long timestamp) {
        this.information = information;
        this.productfee = productfee;
        this.shipaddress = shipaddress;
        this.shipmentfee = shipmentfee;
        this.status = status;
        this.totalpayment = totalpayment;
        this.recipient = recipient;
        this.phone = phone;
        this.timestamp = timestamp;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getProductfee() {
        return productfee;
    }

    public void setProductfee(String productfee) {
        this.productfee = productfee;
    }

    public String getShipaddress() {
        return shipaddress;
    }

    public void setShipaddress(String shipaddress) {
        this.shipaddress = shipaddress;
    }

    public String getShipmentfee() {
        return shipmentfee;
    }

    public void setShipmentfee(String shipmentfee) {
        this.shipmentfee = shipmentfee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(String totalpayment) {
        this.totalpayment = totalpayment;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
