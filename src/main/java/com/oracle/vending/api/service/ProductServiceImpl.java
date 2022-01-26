package com.oracle.vending.api.service;

import com.oracle.vending.api.config.ApiConfig;
import com.oracle.vending.api.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    InputOutputService inputOutputService;

    @Autowired
    ApiConfig config;

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public Product getProduct() {
        inputOutputService.println("\n*********************X***********************\n");
        inputOutputService.println("Please select product:");
        List<String> products = config.getProductList();
        List<Float> prices = config.getPriceList();

        for(int i=0;i<products.size();i++){
            inputOutputService.println((i+1) + ". " + products.get(i) + ": " + prices.get(i));
        }
        try{
            int val = inputOutputService.getInt();

            if (val >0 && val<=prices.size()) {
                return new Product(products.get(val-1),prices.get(val-1));
            } else {
                inputOutputService.flushReader();
                inputOutputService.println("Error! Please enter valid product number!");
                return null;
            }
        }catch (Exception exception){
            inputOutputService.flushReader();
            inputOutputService.println("Error! Please try again with integer numbers!");
            return null;
        }
    }
}
