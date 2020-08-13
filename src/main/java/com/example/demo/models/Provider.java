package com.example.demo.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Provider {
    String id, name, transaction_info;
    String transaction_date;

    List<Transaction_Info> transaction_info_array;

    public Provider(String id, String name, Date transaction_date, String transaction_info){
        this.id = id;
        this.name = name;
        this.transaction_date = transaction_date.toString();
        this.transaction_info = transaction_info;
    }

    public Provider() {

    }

    public Provider(String record) {

        String [] values = record.split("#");

        this.setId(values[0]);
        this.setName(values[1]);
        this.setDate(values[2]);
        this.setTransaction_info(values[3]);

        this.setTransaction_infos();
    }

    public void setTransaction_infos() {
        String[] temp = this.transaction_info.split("\\|");
        List<Transaction_Info> transaction_infoList = new ArrayList<>();
        for(String record: temp){
            Transaction_Info transaction_info = new Transaction_Info();
            transaction_info.setFull_info(record);
            transaction_info.setAmount(Long.parseLong(record.split(",")[0].split(":")[1]));
            transaction_info.setAddress(record.split(",")[1].split(":")[1]);
            transaction_info.setTIN(record.split(",")[2].split(":")[1]);

            transaction_infoList.add(transaction_info);
        }

        this.setTransaction_infos(transaction_infoList);
    }

    public List<Transaction_Info> getTransaction_infos() {
        return transaction_info_array;
    }

    public void setTransaction_infos(List<Transaction_Info> transaction_infos){
        this.transaction_info_array = transaction_infos;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTransaction_date() {
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
        this.transaction_date = transaction_date.toString();
    }

    public void setDate(String transaction_date) {
        this.transaction_date = transaction_date.toString();
    }

    public void setTransaction_info(String transaction_info) {
        this.transaction_info = transaction_info;
    }
}

