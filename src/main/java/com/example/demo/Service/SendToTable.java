package com.example.demo.Service;

import com.example.demo.data.ProviderDao;
import com.example.demo.data.ProviderRecords;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("SendToTable")
public class SendToTable {

    private ProviderDao providerDao;

    public SendToTable(@Qualifier("mysql") ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    public void sendData(String value){
        providerDao.SendDataToTable(value);
    }

}
