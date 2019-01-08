package com.example.dan.ahiro.Model;

public class Order {

    public String information, productfee, shipaddress, shipmentfee, status, timestamp, totalpayment;

    public Order(){}

    public Order(String information, String productfee, String shipaddress, String shipmentfee, String status, String timestamp, String totalpayment) {
        this.information = information;
        this.productfee = productfee;
        this.shipaddress = shipaddress;
        this.shipmentfee = shipmentfee;
        this.status = status;
        this.timestamp = timestamp;
        this.totalpayment = totalpayment;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotalpayment() {
        return totalpayment;
    }

    public void setTotalpayment(String totalpayment) {
        this.totalpayment = totalpayment;
    }
}
