package com.acme.fueltrack.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class FuelTrackBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuelTrackBackEndApplication.class, args);
    }

}
