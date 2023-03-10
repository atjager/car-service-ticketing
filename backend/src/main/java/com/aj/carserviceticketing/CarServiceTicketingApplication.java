package com.aj.carserviceticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.aj.carserviceticketing"})
public class CarServiceTicketingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarServiceTicketingApplication.class, args);
    }

}