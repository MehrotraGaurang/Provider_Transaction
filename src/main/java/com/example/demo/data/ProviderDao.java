package com.example.demo.data;

import com.example.demo.models.Provider;
import com.example.demo.models.Provider_Transaction;

import java.sql.SQLException;
import java.util.List;

public interface ProviderDao {
    void selectAllPeople() throws SQLException;

    void SendDataToTable(String s);

    List<Provider_Transaction> providers();

    void insertProvider(Provider provider);
}
