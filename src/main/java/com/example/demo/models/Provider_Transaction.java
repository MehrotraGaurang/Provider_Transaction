package com.example.demo.models;

public class Provider_Transaction {
    private String id, name, isFraud, TIN;
    private Long amount;

    public Provider_Transaction() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsFraud(String isFraud) {
        this.isFraud = isFraud;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIsFraud() {
        return isFraud;
    }

    public String getTIN() {
        return TIN;
    }

    public Long getAmount() {
        return amount;
    }

    public Provider_Transaction(String id, String name, String isFraud, String TIN, Long amount) {
        this.id = id;
        this.name = name;
        this.isFraud = isFraud;
        this.TIN = TIN;
        this.amount = amount;
    }
}
