package com.example.demo.data;

import com.example.demo.properties.PropertiesConfig;
import com.example.demo.models.Provider;
import com.example.demo.models.Provider_Transaction;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository("mysql")
public class ProviderRecords extends JdbcDaoSupport implements ProviderDao{

    @Autowired
    DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostConstruct
    private void initialize() {setDataSource(dataSource);}

    private PropertiesConfig propertiesConfig = new PropertiesConfig();
    private Producer<String, String> producer;

    private Producer<String, String> getInstance(){

        Properties properties = propertiesConfig.createProperties();
        properties = propertiesConfig.createProperties();

        if(this.producer == null) {
            this.producer = new KafkaProducer<String, String>(properties);
        }
        return producer;
    }

    @PostConstruct
    public void selectAllPeople() throws SQLException {
        String statement = "SELECT * FROM providers;";

        List<Provider> providers = jdbcTemplate.query(
                statement,
                new ResultSetExtractor<List<Provider>>() {
                    @Override
                    public List<Provider> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<Provider> list = new ArrayList<>();

                        while(resultSet.next()){
                            Provider provider = new Provider();
                            provider.setId(resultSet.getString("id"));
                            provider.setName(resultSet.getString("prov_name"));
                            provider.setDate(resultSet.getDate("trans_date"));
                            provider.setTransaction_info(resultSet.getString("transaction_info"));
                            list.add(provider);
                        }

                        return list;
                    }
                });

        for(Provider provider: providers){
            try{

                System.out.println(provider.getName() + "\n");
                String value =
                        provider.getId() + "#" + provider.getName() + "#" + provider.getTransaction_date().toString() + "#" + provider.getTransaction_info();
                ProducerRecord<String, String> record = new ProducerRecord<>(propertiesConfig.getTopicInitial(), provider.getId(), value);

                getInstance().send(record);

                System.out.println("********SENT********* " + record.key() + record.value());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void SendDataToTable(String s){
        String[] values = s.split(",");

        String id = values[0];
        String name = values[1];
        String isFraud = values[2];
        Long amount = Long.valueOf(values[3]);
        String TIN = values[4];

        String statement = "INSERT INTO provider_transaction VALUES (?,?,?,?,?)";

        jdbcTemplate.update(statement, id, name, isFraud, amount, TIN);
    }

    @Override
    public List<Provider_Transaction> providers() {
        List<String> result = new ArrayList<>();

        String query = "SELECT * FROM provider_transaction";

        return jdbcTemplate.query(
                query,
                new ResultSetExtractor<List<Provider_Transaction>>() {
                    @Override
                    public List<Provider_Transaction> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                        List<Provider_Transaction> list = new ArrayList<>();

                        while(resultSet.next()){
                            Provider_Transaction provider = new Provider_Transaction();
                            provider.setId(resultSet.getString("ID"));
                            provider.setName(resultSet.getString("Name_Provider"));
                            provider.setIsFraud(resultSet.getString("Is_Fraud"));
                            provider.setAmount(resultSet.getLong("Amount"));
                            provider.setTIN(resultSet.getString("TIN"));
                            list.add(provider);
                        }

                        return list;
                    }
                });
    }

    @Override
    public void insertProvider(Provider provider) {
        String query = "INSERT INTO providers VALUES (?,?,?,?);";

        getJdbcTemplate()
                .update(query, provider.getId(), provider.getName(), provider.getDate_Date(), provider.getTransaction_info());
    }
}
