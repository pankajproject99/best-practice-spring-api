package com.example.bestpracticespringapi.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
//@EnableWebSecurity
@Slf4j
public class SecurityConfig1BasicAuth {

    @Bean
    public SecurityFilterChain myFilterChain(HttpSecurity http) throws Exception {
        log.info("######### Authenticating");
        http.authorizeHttpRequests()
                .requestMatchers("/home","/register","/saveUser").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(withDefaults())
                .logout((logout) -> logout.permitAll())
//                .httpBasic(withDefaults())
        ;

        return http.build();
    }
}