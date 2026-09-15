package com.security.authserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(2147483642)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        // http.authorizeHttpRequests((requests) -> (requests.anyRequest()).permitAll());
        // http.authorizeHttpRequests((requests) -> (requests.anyRequest()).denyAll());
        http.authorizeHttpRequests((requests) -> 
            requests.requestMatchers("/welcome","/test1","/test2").authenticated()
            .requestMatchers("/help","/error").permitAll());

            // http.formLogin(flc -> flc.disable());
            // http.httpBasic(hbc -> hbc.disable());
            http.formLogin(Customizer.withDefaults());
            http.httpBasic(Customizer.withDefaults());
            return (SecurityFilterChain)http.build();
    }
}
