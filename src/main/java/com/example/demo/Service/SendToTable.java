package com.example.demo.Service;

import com.example.demo.data.ProviderDao;
import com.example.demo.data.ProviderRecords;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("SendToTable")
public class SendToTable {

    @Autowired
    private ProviderDao providerDao;

    public void sendData(String value){
        providerDao.SendDataToTable(value);
    }

}
