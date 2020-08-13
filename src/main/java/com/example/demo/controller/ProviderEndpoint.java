package com.example.demo.controller;

import com.example.demo.Service.DisplayData;
import com.example.demo.models.Provider_Transaction;
import com.google.gson.Gson;
import kafka.utils.Json;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProviderEndpoint {

    private DisplayData displayData;

    @Autowired
    public ProviderEndpoint(@Qualifier("display") DisplayData displayData) {
        this.displayData = displayData;
    }

    @GetMapping("/show")
    public List<Map<String, String>> show_transactions(){

        List<Map<String, String>> list = new ArrayList<>();
//        Gson gson = new Gson();
        List<Provider_Transaction> providers = displayData.getProviders();

//        String result = "{";

        for(Provider_Transaction provider: providers){
            Map<String, String> val = new HashMap<>();
            val.put("ID", provider.getId());
            val.put("Name_Provider", provider.getName());
            val.put("Is_Fraud", provider.getIsFraud());
            val.put("Amount", provider.getAmount().toString());
            val.put("TIN", provider.getTIN());
//            String json = gson.toJson(val);

//            result += json;

            list.add(val);
        }

//        result += "}";

        return list;
    }
}
