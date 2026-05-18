package com.thenexus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                
                // 1. PUBLIC ROUTES: Anyone can view your landing page, styles, and error pages
                .requestMatchers("/", "/index.html", "/error", "/css/**", "/js/**", "/images/**").permitAll()
                
                // 2. OAUTH ROUTES: Keep the login triggers open
                .requestMatchers("/login/oauth2/code/**", "/oauth2/authorization/**").permitAll()
                
                // 3. PROTECTED ROUTES: Only logged-in users can see the actual portal dashboard
                .requestMatchers("/dashboard", "/portal/**").authenticated()
                
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                // 4. If an unauthenticated user tries to hit a protected page, send them to our public landing page instead of auto-triggering Google
                .loginPage("/") 
                // 5. Land here upon a successful click-login
                .defaultSuccessUrl("/dashboard", true)
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
