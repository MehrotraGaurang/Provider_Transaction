package com.example.demo.models;

import com.google.gson.annotations.SerializedName;

public class Provider_Transaction {

    @SerializedName("prov_id")
    String id;
    @SerializedName("prov_name")
    String name;
    @SerializedName("prov_fraud")
    String isFraud;
    @SerializedName("prov_tin")
    String TIN;
    @SerializedName("prov_amount")
    Long amount;

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
