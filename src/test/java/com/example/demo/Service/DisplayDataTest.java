package com.example.demo.Service;

import com.example.demo.data.ProviderDao;
import com.example.demo.models.Provider_Transaction;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class DisplayDataTest {

    @InjectMocks
    private DisplayData displayData;

    @Mock
    private ProviderDao providerDao;

    @Before
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void test() {
//        ProviderDao mock = Mockito.mock(ProviderDao.class);

        Provider_Transaction provider_transaction = new Provider_Transaction(
                "10",
                "ABC",
                "FALSE",
                "100",
                900L
        );

        List<Provider_Transaction> sample = Arrays.asList(provider_transaction);
        System.out.println(sample.get(0).getName());

        when(providerDao.providers()).thenReturn(sample);

        assertEquals(1, displayData.getProviders().size());
    }

}