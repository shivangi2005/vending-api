package com.oracle.vending.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiConfig {

    @Autowired
    private Environment environment;

    private  List<String> productList;
    private  List<Float> priceList;

    @PostConstruct
    public void populateVaribales() {

        this.productList = Arrays.asList(environment.getProperty("vending.machine.products").split(","));
        List<String> priceStrs = Arrays.asList(environment.getProperty("vending.machine.prices").split(","));
        this.priceList = priceStrs.stream().map(price -> Float.valueOf(price)).collect(Collectors.toList());
    }

    public List<String> getProductList() {
        return productList;
    }

    public List<Float> getPriceList() {
        return priceList;
    }
}
