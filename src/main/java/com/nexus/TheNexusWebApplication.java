package com.nexus; // Ensure this matches your folder path exactly

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheNexusWebApplication {

    public static void main(String[] args) {
        // This is the power switch that starts TheNexusWeb
        SpringApplication.run(TheNexusWebApplication.class, args);
    }
}
