package com.ninebudget.config;

import com.ninebudget.filter.AuthFilter;
import com.ninebudget.model.JWTToken;
import com.ninebudget.model.Token;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Bean
    @Scope("prototype")
    public Token jwtToken(){
        return new JWTToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
