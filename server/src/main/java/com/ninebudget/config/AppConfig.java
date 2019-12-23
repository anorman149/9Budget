package com.ninebudget.config;

import com.ninebudget.component.AccountComponent;
import com.ninebudget.component.BudgetComponent;
import com.ninebudget.dao.*;
import com.ninebudget.filter.AuthFilter;
import com.ninebudget.model.CacheVendor;
import com.ninebudget.model.JWTToken;
import com.ninebudget.vendor.Redis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:store.properties")
public class AppConfig {
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
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
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
