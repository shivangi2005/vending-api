package com.oracle.vending.api.service;

import com.oracle.vending.api.config.ApiConfig;
import com.oracle.vending.api.model.Change;
import com.oracle.vending.api.model.MachineState;
import com.oracle.vending.api.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    InputOutputService inputOutputService;

    @Mock
    ApiConfig config;

    @InjectMocks
    ProductServiceImpl productServiceMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testInitialise(){
        List<String> productList = Arrays.asList(("Walkers Cheese & Onion,Walkers Ready Salted,Twix Bar,Cadbury Twirl").split(","));
        when(config.getProductList()).thenReturn(productList);
        List<String> priceStrs = Arrays.asList(("0.30,0.30,1.2,1.5").split(","));
        List<Float> priceList = priceStrs.stream().map(price -> Float.valueOf(price)).collect(Collectors.toList());
        when(config.getPriceList()).thenReturn(priceList);
        when(inputOutputService.getInt()).thenReturn(1);
        Product product = productServiceMock.getProduct();
        assertEquals(0.3f,product.getPrice());
    }

}