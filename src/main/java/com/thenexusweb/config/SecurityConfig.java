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
            // Disable CSRF tokens to keep web forms from being blocked on the live server
            .csrf(csrf -> csrf.disable())
            
            // 🌐 LIVE ACCESS ROUTING MAP
            .authorizeHttpRequests(auth -> auth
                // Let anyone see the root home page layout without being logged in
                .requestMatchers("/").permitAll()
                // Let anyone see the login layout screen, signup page, and branding assets freely
                .requestMatchers("/login", "/signup", "/favicon.svg").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                // Everything else (like Nexus Social or Movies Hub) requires an active login session
                .anyRequest().authenticated()
            )
            
            // 👤 INJECT CUSTOM FORM LOGIN LAYER (OUR BLUE DESIGN)
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            
            // 🌐 OVERWRITE GOOGLE SINGLE SIGN-ON LOOP
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            
            // 🚪 SECURITY SESSION DISCONNECT
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
