package com.example.demo.properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@SuppressWarnings("unchecked")
public class PropertiesConfig {

    private String topicInitial = "PROVIDER_INITIAL";
    private String topicFinal = "PROVIDER_FINAL";
    private String bootstrap = "broker:9092";

    public Properties createProperties(){
        Properties properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "DEMO_APPLICATION");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrap);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "Group2");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        return properties;
    }

    public String getTopicInitial(){
        return topicInitial;
    }

    public String getTopicFinal() {
        return topicFinal;
    }

    public String getBootstrap() {
        return bootstrap;
    }
}
