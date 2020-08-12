package com.example.demo.Service;

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

    private HashMap<String, String> record = new HashMap<>();
    private HashSet<String> checkExists = new HashSet<>();
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
    public void process(String s, String provider) {

        String [] values = provider.split("#");

        String transaction_info = values[3];

        String isFraud = "FALSE";

        if(checkExists.contains(transaction_info)){
            isFraud = "TRUE";
            fraudRecordDetected(values[0], values);
        }
        else
        {
            List<String> transactions = Arrays.asList(transaction_info.split("\\|"));
            Long total_amount = 0L;
            List<String> TIN_ALL = new ArrayList<>();
            System.out.println("\n" + transaction_info + "\n");
            System.out.println("\n************* " + transactions.get(0) + " ***************\n") ;
            System.out.println("\n************* " + transactions.get(0).split(",")[2] + " ***************\n") ;
            System.out.println("\n************* " + transactions.get(0).split(",")[2].split("\\:")[1] + " ***************\n") ;

            for(String transaction: transactions){
                String amount = transaction.split(",")[0];

                String TIN = transaction.split(",")[2].split("\\:")[1];

                TIN_ALL.add(TIN);

                Long amount_value = Long.parseLong(amount.split("\\:")[1]);

                total_amount += amount_value;
            }

            String TIN = String.join("~", TIN_ALL);

            String value = values[0] + "," + values[1] + "," + isFraud + "," + total_amount.toString() + "," + TIN;

            generateForwardData(values[0], value);
        }

    }

    private void fraudRecordDetected(String key, String[] value) {
        String record = value[0] + "," + value[1] + ",TRUE,-1,FRAUD";

        ProducerRecord<String, String> record_send = new ProducerRecord<>
                (propertiesConfig.getTopicFinal(), key, record);

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
