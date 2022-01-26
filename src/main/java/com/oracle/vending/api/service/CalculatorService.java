package com.oracle.vending.api.service;


import com.oracle.vending.api.model.Change;


public interface CalculatorService {
    Change calculateChange(Change machine, Change customer, float price);
    Change acceptChange(float price);

}
