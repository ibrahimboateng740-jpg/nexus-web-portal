package com.thenexusweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🌐 ACCESS CONTROL SETTINGS
            .authorizeHttpRequests(auth -> auth
                // Allow anyone to access static assets, your icon, the homepage, and signup without being logged in
                .requestMatchers("/", "/signup", "/favicon.svg", "/css/**", "/js/**").permitAll()
                // Everything else (like our custom login check) requires security clearance
                .anyRequest().authenticated()
            )
            
            // 👤 STRIP DEFAULT FORM LOGIN & INJECT YOUR BLUE CUSTOM PAGE
            .formLogin(form -> form
                // This forces Spring Boot to look directly at your custom login.html file
                .loginPage("/login")
                // Redirects users straight back to your dashboard homepage upon successful authentication
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            
            // 🌐 STRIP DEFAULT OAUTH WHITE SCREEN & INTEGRATE GOOGLE SIGN-IN
            .oauth2Login(oauth -> oauth
                // This tells Google's authentication loop to also trigger inside your custom layout page
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            
            // 🚪 SESSION SEVERANCE DISCONNECT UTILITY
            .logout(logout -> logout
                // Clears the cookie tokens and securely drops the user off back at the login screen
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
