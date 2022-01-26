package com.oracle.vending.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

@Configuration
public class AppConfig
{
    @Bean
    public Scanner reader(){
        return new Scanner(System.in);
    }


}
