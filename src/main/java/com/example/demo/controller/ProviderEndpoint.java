package com.example.demo.controller;

import com.example.demo.Service.DisplayData;
import com.example.demo.models.Provider;
import com.example.demo.models.Provider_Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProviderEndpoint {

    @Autowired
    private DisplayData displayData;

    @GetMapping("/show")
    public List<Map<String, String>> show_transactions(){

        List<Map<String, String>> list = new ArrayList<>();
        List<Provider_Transaction> providers = displayData.getProviders();

        for(Provider_Transaction provider: providers){
            Map<String, String> val = new HashMap<>();
            val.put("ID", provider.getId());
            val.put("Name_Provider", provider.getName());
            val.put("Is_Fraud", provider.getIsFraud());
            val.put("Amount", provider.getAmount().toString());
            val.put("TIN", provider.getTIN());

            list.add(val);
        }

        return list;
    }

    @PostMapping("/insertProvider")
    public void insertProvider(@RequestBody Provider provider){
        displayData.insertProvider(provider);
    }
}
