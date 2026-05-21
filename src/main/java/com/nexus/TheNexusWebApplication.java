package com.thenexusweb; // ⚠️ CHANGE THIS to match your exact project package line if different

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class TheNexusWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheNexusWebApplication.class, args);
    }
}

// 🌟 WE INJECT THE CONFIGURATION DIRECTLY HERE SO SPRING CANNOT IGNORE IT
@Configuration
@EnableWebSecurity
class LiveSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF tokens to keep local forms from getting locked out
            .csrf(csrf -> csrf.disable())
            
            // 2. Open up traffic for the root homepage, login layout, and static files
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/signup", "/favicon.svg").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()
                .anyRequest().authenticated()
            )
            
            // 3. Force your custom blue login page layout
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            
            // 4. Force your custom login layout for Google Sign-In too
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            
            // 5. Setup disconnect session utility
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
