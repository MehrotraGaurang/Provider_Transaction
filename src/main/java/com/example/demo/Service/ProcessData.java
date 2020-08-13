package com.example.demo.Service;

import com.example.demo.models.Provider;
import com.example.demo.models.Transaction_Info;
import com.example.demo.properties.PropertiesConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProcessData extends AbstractProcessor<String, String> {

    private Producer<String, String> producer;
    private PropertiesConfig propertiesConfig = new PropertiesConfig();
    private final SendToTable sendToTable;

    @Autowired
    public ProcessData(@Qualifier("SendToTable") SendToTable sendToTable){
        this.sendToTable = sendToTable;
    }

    private Producer<String, String> getInstance(){
        Properties properties = new Properties();
        properties = propertiesConfig.createProperties();

        if(this.producer == null) {
            this.producer = new KafkaProducer<String, String>(properties);
        }
        return producer;
    }

    @Override
    public void process(String s, String record) {

        Provider provider = new Provider(record);

        HashSet<String> checkExists = new HashSet<>();

        List<Transaction_Info> transaction_info = provider.getTransaction_infos();

        Long total_amount = 0L;
        List<String> TIN_ALL = new ArrayList<>();

        for(Transaction_Info transaction: transaction_info){

            if(checkExists.contains(transaction.getFull_info())) {
                fraudRecordDetected(provider);
                return;
            }

            checkExists.add(transaction.getFull_info());

            TIN_ALL.add(transaction.getTIN());

            total_amount += transaction.getAmount();
        }

        String TIN = String.join("~", TIN_ALL);

        String value = provider.getId() + "," + provider.getName() + "," + "FALSE" + "," + total_amount.toString() + "," + TIN;

        generateForwardData(provider.getId(), value);


    }

    private void fraudRecordDetected(Provider provider) {
        String record = provider.getId() + "," + provider.getName() + ",TRUE,-1,FRAUD";

        ProducerRecord<String, String> record_send = new ProducerRecord<>
                (propertiesConfig.getTopicFinal(), provider.getId(), record);

        sendToTable.sendData(record);

        getInstance().send(record_send);

        String[] values = record.split(",");

        System.out.println(
                "************** "  +
                        values[0] +
                        " " +
                        values[1] +
                        " " +
                        values[2] +
                        " " +
                        values[3] +
                        " " +
                        values[4] +
                        "\n"
        );
    }

    private void generateForwardData(String key, String value) {

        ProducerRecord<String, String> record = new ProducerRecord<>
                (propertiesConfig.getTopicFinal(), key, value);

        getInstance().send(record);

        sendToTable.sendData(value);

        String[] values = value.split(",");

        System.out.println(
                "************** "  +
                values[0] +
                " " +
                values[1] +
                " " +
                values[2] +
                " " +
                values[3] +
                " " +
                values[4] +
                "\n"
        );

        context().commit();
    }

}
