package com.example.demo.Service;

import com.example.demo.properties.PropertiesConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.Assert.*;

@SpringBootTest
@DirtiesContext
@RunWith(PowerMockRunner.class)
public class ProcessDataTest {
    private PropertiesConfig propertiesConfig = new PropertiesConfig();
    private ProcessData processData = new ProcessData();

    @BeforeTestClass
    public void mainSetup() throws Exception{}

    @AfterTestClass
    public static void teardown(){}

    @Test
    public void testProcessData() {
        try{
            setupData();
        } catch (Exception e) {
            System.out.println("Test failed!");
            e.printStackTrace();
        }
    }

    public void setupData() {

//        PowerMockito.mockStatic(AvroUtil.class);

        String key = "101";
        String value = "101#MAX#2020-08-09#Amount:18000,Address:ABC,TIN:099|Amount:90000,Address:ABC,TIN:210|Amount:18090,Address:ABC,TIN:101";
        processData.process(key, value);
    }

}