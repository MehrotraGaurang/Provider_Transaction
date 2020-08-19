package com.example.demo.models;

import com.google.gson.Gson;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Provider_TransactionTest {

    private Provider_Transaction provider_transaction;

    String record;

    @Test
    public void test() {
        record = "{\n" +
                "  \"prov_id\":\"100\",\n" +
                "  \"prov_name\":\"ABC\",\n" +
                "  \"prov_fraud\":\"FRAUD\",\n" +
                "  \"prov_tin\":\"TIN\",\n" +
                "  \"prov_amount\":1000\n" +
                "}";

        provider_transaction = new Gson().fromJson(record.replace("'", ""), Provider_Transaction.class);

        assertEquals("100", provider_transaction.id);
        assertEquals("ABC", provider_transaction.name);
        assertEquals("FRAUD", provider_transaction.isFraud);
        assertEquals("TIN", provider_transaction.TIN);
        assertEquals(1000, provider_transaction.amount);
    }

}