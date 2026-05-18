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
                // 1. Allow the application's underlying forward actions to fire
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                // 2. Open up the public pages, errors, and static asset folders
                .requestMatchers("/", "/login**", "/error**", "/css/**", "/js/**", "/images/**").permitAll()
                // 3. Keep the actual OAuth authentication pipeline completely open
                .requestMatchers("/login/oauth2/code/**", "/oauth2/authorization/**").permitAll()
                // 4. Any other page execution requires a completely authenticated session state
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                // 5. Explicitly drop the logged-in user at the home landing page dashboard
                .defaultSuccessUrl("/home", true)
            )
            .logout(logout -> logout
                // 6. Provide a clean exit strategy that wipes the proxy session cleanly
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
}
