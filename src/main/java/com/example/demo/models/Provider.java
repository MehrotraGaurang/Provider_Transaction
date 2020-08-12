package com.example.demo.models;

import java.util.Date;
import java.util.UUID;

public class Provider {
    String id, name, transaction_info;
    Date transaction_date;

    public Provider(String id, String name, Date transaction_date, String transaction_info){
        this.id = id;
        this.name = name;
        this.transaction_date = transaction_date;
        this.transaction_info = transaction_info;
    }

    public Provider() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public String getTransaction_info() {
        return transaction_info;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public void setTransaction_info(String transaction_info) {
        this.transaction_info = transaction_info;
    }
}

