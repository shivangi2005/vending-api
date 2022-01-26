package com.oracle.vending.api.service;

public interface InputOutputService {
    Integer getInt();
    String getString();
    void println(String str);
    void flushReader();
    void print(String str);
}
