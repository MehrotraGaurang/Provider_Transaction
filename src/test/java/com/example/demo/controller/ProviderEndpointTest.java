package com.example.demo.controller;

import com.example.demo.Service.DisplayData;
import com.example.demo.models.Provider_Transaction;
import com.example.demo.models.Transaction_Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(PowerMockRunner.class)
public class ProviderEndpointTest {

    @InjectMocks
    ProviderEndpoint providerEndpoint;

    @Mock
    DisplayData displayData;

    @Test
    public void test() {
        Provider_Transaction provider_transaction = new Provider_Transaction(
                "100",
                "ABC",
                "true",
                "100",
                1000L
        );

        List<Provider_Transaction> sample = Arrays.asList(provider_transaction);

        when(displayData.getProviders()).thenReturn(sample);

        List<Map<String, String>> record = providerEndpoint.show_transactions();

        assertEquals("100", record.get(0).get("ID"));
        assertEquals("ABC", record.get(0).get("Name_Provider"));
        assertEquals("true", record.get(0).get("Is_Fraud"));
        assertEquals("100", record.get(0).get("TIN"));
        assertEquals("1000", record.get(0).get("Amount"));
    }
}