package com.ninebudget.config;

import com.ninebudget.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public AccountDAO accountDAO(){
        return new AccountDAO();
    }

    @Bean
    public CategoryDAO categoryDAO(){
        return new CategoryDAO();
    }

    @Bean
    public BudgetDAO budgetDAO(){
        return new BudgetDAO();
    }

    @Bean
    public InstitutionDAO institutionDAO(){
        return new InstitutionDAO();
    }

    @Bean
    public UserDAO userDAO(){
        return new UserDAO();
    }
}
