package com.example.demo.properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesConfigTest {

    @Autowired
    private PropertiesConfig propertiesConfig = new PropertiesConfig();

    @Test
    public void show() {
//        Properties properties = propertiesConfig.createProperties();
        assertEquals("localhost:9092", propertiesConfig.getBootstrap());
    }

}