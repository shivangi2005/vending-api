package com.oracle.vending.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

@Service
public class InputOutputServiceImpl implements InputOutputService{

    @Autowired
    private Scanner scanner;

    @Override
    public Integer getInt() {
        return scanner.nextInt();
    }

    @Override
    public String getString() {
        return scanner.next();
    }

    @Override
    public void flushReader(){
        scanner.nextLine();
    }

    @Override
    public void println(String str) {
        System.out.println(str);
    }

    @Override
    public void print(String str) {
        System.out.print(str);
    }
}
