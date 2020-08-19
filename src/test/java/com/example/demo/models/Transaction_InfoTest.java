package com.example.demo.models;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Transaction_InfoTest {

    Transaction_Info transaction_info;

    String record;

    @Test
    public void test() {
        record = "{\n" +
                "  \"prov_address\":\"ABC\",\n" +
                "  \"prov_tin\":\"100\",\n" +
                "  \"prov_amount\":1000\n" +
                "}";

        System.out.println(record);

        transaction_info = new Gson().fromJson(record.replace("'", ""), Transaction_Info.class);

        assertEquals("ABC", transaction_info.Address);
        assertEquals("100", transaction_info.TIN);
        assertEquals(1000, transaction_info.amount);
    }

}