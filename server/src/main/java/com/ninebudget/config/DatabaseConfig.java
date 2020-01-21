package com.ninebudget.config;

import com.ninebudget.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    private static final String DATASOURCE_CONTEXT = "java:/BudgetDS";

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

    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new InitialContext().lookup(DATASOURCE_CONTEXT);
    }
}
