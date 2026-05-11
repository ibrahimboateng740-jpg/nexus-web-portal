package com.nexus.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortalApplication {
    public static void main(String[] args) {
        // This is the line that starts the entire web server
        SpringApplication.run(PortalApplication.class, args);
    }
}
