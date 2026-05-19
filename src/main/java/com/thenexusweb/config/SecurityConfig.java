package com.thenexus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ⚡ THE CRITICAL BYPASS: This completely rips the public pages out of Spring's security filter chain.
    // This absolutely guarantees that typing your URL loads index.html instead of triggering Google.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
            "/", 
            "/index.html", 
            "/login", 
            "/signup", 
            "/error", 
            "/css/**", 
            "/js/**", 
            "/images/**"
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                
                // Keep the OAuth processing pipelines open internally
                .requestMatchers("/login/oauth2/code/**", "/oauth2/authorization/**").permitAll()
                
                // SECURE PATHS: Your home sidebar dashboard requires authentication
                .requestMatchers("/home").authenticated()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                // If an unauthenticated user attempts to access /home, redirect them to our public /login page
                .loginPage("/login")
                // On a successful button click, drop them on your original sidebar dashboard
                .defaultSuccessUrl("/home", true)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
}
