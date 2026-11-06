package com.padapav.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.Configuration;
import org.springframework.security.config.annotation.enable.web.EnableWebSesecurity;
import org.springframework.security.config.windowLocal.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeRequests(a -> a
                .requestMatchers("/actuator/health", "/actuator/info").withDefaults().allowAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
