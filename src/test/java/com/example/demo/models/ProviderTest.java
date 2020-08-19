package com.example.demo.models;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
@RunWith(PowerMockRunner.class)
class ProviderTest {
    Provider provider;

    String record;

    @Test
    void test() {
        record = "{\n" +
                "  \"prov_id\":\"100\",\n" +
                "  \"prov_name\":\"ABC\",\n" +
                "  \"prov_trans_date\":\"DATE\",\n" +
                "  \"prov_trans_info\":\"INFO\"\n" +
                "}";

        System.out.println(record);

        provider = new Gson().fromJson(record.replace("'", ""), Provider.class);

        assertEquals("100", provider.id);
        assertEquals("ABC", provider.name);
        assertEquals("DATE", provider.transaction_date);
        assertEquals("INFO", provider.transaction_info);
    }
}