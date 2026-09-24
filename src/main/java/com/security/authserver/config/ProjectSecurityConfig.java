package com.security.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.config.Customizer;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    @Order(2147483642)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        // http.authorizeHttpRequests((requests) -> (requests.anyRequest()).permitAll());
        // http.authorizeHttpRequests((requests) -> (requests.anyRequest()).denyAll());
        http
            .cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:80","http://localhost"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
            .csrf(csrfConfig -> csrfConfig.disable())
            .authorizeHttpRequests((requests) ->
                requests
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/help","/error","/login").permitAll()
                      /*.requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                        .requestMatchers("/myBalance").hasAnyAuthority("VIEWBALANCE", "VIEWACCOUNT")*/
                        .requestMatchers("/test2").hasRole("ADMIN")
                        .requestMatchers("/test1").hasAnyRole("USER")
                        .requestMatchers("/user").authenticated()
                    .requestMatchers("/welcome","/test1","/test2").authenticated()
                );

            // http.formLogin(flc -> flc.disable());
            // http.httpBasic(hbc -> hbc.disable());
            http.formLogin(Customizer.withDefaults());
            http.httpBasic(Customizer.withDefaults());
            return (SecurityFilterChain)http.build();
    }

// //    @Bean
//     UserDetailsService inmemoryUserDetailService(){
//         UserDetails admin = User.withUsername("admin").password("{noop}admin").authorities("admin").build();
//         UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$6BCsW1QfD/bG7UFvWNzev.ptRJVF6/fraiD.kTlNFOtCS1fO31YXK").authorities("user").build();
//         return new InMemoryUserDetailsManager(admin,user);
//     }

    // @Bean
    // UserDetailsService jdbcUserDetailService(DataSource dataSource){
    //     return new JdbcUserDetailsManager(dataSource);
    // }

    @Bean 
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
