package com.hanna.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OrderBillingWsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OrderBillingWsApplication.class, args);
    }
}