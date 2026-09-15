package com.security.authserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    @Bean 
    UserDetailsService userDetailService(){
        UserDetails admin = User.withUsername("admin").password("{noop}admin").authorities("admin").build();
        UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$6BCsW1QfD/bG7UFvWNzev.ptRJVF6/fraiD.kTlNFOtCS1fO31YXK").authorities("user").build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    @Bean 
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
