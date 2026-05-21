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
            // Disable CSRF tokens to ensure live web forms post correctly on Render
            .csrf(csrf -> csrf.disable())
            
            // 🌐 LIVE ACCESS ROUTING MAP
            .authorizeHttpRequests(auth -> auth
                // Allow public viewing of the homepage, login page, and signup page
                .requestMatchers("/", "/login", "/signup", "/favicon.svg").permitAll()
                // Allow public access to all asset folders so your design templates render correctly
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                // Any other internal dashboard route requires an active authenticated session
                .anyRequest().authenticated()
            )
            
            // 👤 INJECT CUSTOM FORM LOGIN LAYER (OUR BLUE DESIGN)
            .formLogin(form -> form
                .loginPage("/login")
                // 🌟 FIX: We removed the duplicate loginProcessingUrl to break the loop!
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
