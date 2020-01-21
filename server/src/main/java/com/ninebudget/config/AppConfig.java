package com.ninebudget.config;

import com.ninebudget.component.AccountComponent;
import com.ninebudget.component.BudgetComponent;
import com.ninebudget.filter.AuthFilter;
import com.ninebudget.model.CacheVendor;
import com.ninebudget.model.JWTToken;
import com.ninebudget.vendor.Redis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration

public class AppConfig {
    @Bean
    public BudgetComponent budgetComponent(){
        return new BudgetComponent();
    }

    @Bean
    public AccountComponent accountComponent(){
        return new AccountComponent();
    }

    @Bean
    public CacheVendor cacheVendor(){
        return new Redis();
    }

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Bean
    @Scope("prototype")
    public JWTToken jwtToken(){
        return new JWTToken();
    }
}
