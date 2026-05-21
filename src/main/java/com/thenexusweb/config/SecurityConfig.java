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
            // Completely disable cross-site request tokens to avoid initial asset blocks
            .csrf(csrf -> csrf.disable())
            
            // 🌐 OPEN ACCESS ROUTING MAP
            .authorizeHttpRequests(auth -> auth
                // 🌟 FIX: Explicitly permits anyone to access the root home page layout without being logged in
                .requestMatchers("/").permitAll()
                // Let anyone see the login screen, signup page, and branding assets freely
                .requestMatchers("/login", "/signup", "/favicon.svg").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                // Everything else (like Nexus Social or Movies Hub) still requires an active account node session
                .anyRequest().authenticated()
            )
            
            // 👤 INJECT CUSTOM FORM LOGIN LAYER
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true) // Sends users directly to home page after standard form login
                .permitAll()
            )
            
            // 🌐 OVERWRITE GOOGLE SINGLE SIGN-ON LOOP
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .defaultSuccessUrl("/", true) // Sends users directly to home page after Google login
            )
            
            // 🚪 SECURITY SESSION DISCONNECT DISCOVERY
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
