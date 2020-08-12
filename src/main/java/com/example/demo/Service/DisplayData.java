package com.example.demo.Service;

import com.example.demo.data.ProviderDao;
import com.example.demo.models.Provider_Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("display")
public class DisplayData {

    private ProviderDao providerDao;

    @Autowired
    public DisplayData(@Qualifier("mysql") ProviderDao providerDao){
        this.providerDao = providerDao;
    }

    public List<Provider_Transaction> getProviders(){
        return providerDao.providers();
    }
}
