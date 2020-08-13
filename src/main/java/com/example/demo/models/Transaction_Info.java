package com.example.demo.models;

public class Transaction_Info {

    String Address;
    String TIN;

    public String getFull_info() {
        return full_info;
    }

    public void setFull_info(String full_info) {
        this.full_info = full_info;
    }

    public Transaction_Info(String address, String TIN, String full_info, Long amount) {
        Address = address;
        this.TIN = TIN;
        this.full_info = full_info;
        this.amount = amount;
    }

    String full_info;
    Long amount;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Transaction_Info(String Address, String TIN, Long amount) {
        this.Address = Address;
        this.TIN = TIN;
        this.amount = amount;
    }

    public Transaction_Info(){}

    public String getTIN() {
        return TIN;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
