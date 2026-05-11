package com.nexus.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Adding (exclude = ...) turns off the login page so you can see your site
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
