package com.example.demo.Service;

import com.example.demo.properties.PropertiesConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;


class AggregateTest {

    @InjectMocks
    private Aggregate aggregate = new Aggregate();

    @Mock
    private PropertiesConfig propertiesConfig = Mockito.mock(PropertiesConfig.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {

        Properties kafkaProperties = new Properties();
        kafkaProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "DEMO_APPLICATION");
        kafkaProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        when(propertiesConfig.createProperties()).thenReturn(kafkaProperties);

        aggregate.init();
    }

}