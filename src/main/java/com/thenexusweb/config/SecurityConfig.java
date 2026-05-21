package com.thenexusweb.config; // Ensure this package line matches its exact folder path

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
            // 1. Completely disable cross-site requests to prevent backend form blocking
            .csrf(csrf -> csrf.disable())
            
            // 2. Open up the web traffic gates completely for asset layout folders
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/signup", "/", "/favicon.svg", "/static/**", "/templates/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            
            // 3. Inject your custom blue dashboard login layout page framework
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login") // Matches your HTML form action attribute exactly
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            
            // 4. Overwrite Google Single Sign-On intercept gates
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            
            // 5. Setup session clearance disconnect path
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
