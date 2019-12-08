package com.ninebudget.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ninebudget.component.BudgetComponent;
import com.ninebudget.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:datasource.properties")
public class AppConfig {
    @Value( "${datasource.url}" )
    private String datasourceURL;

    @Value( "${datasource.driverClass}" )
    private String datasourceDriver;

    @Value( "${datasource.username}" )
    private String datasourceUsername;

    @Value( "${datasource.password}" )
    private String datasourcePass;

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
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(datasourceDriver);
        cpds.setJdbcUrl(datasourceURL);
        cpds.setUser(datasourceUsername);
        cpds.setPassword(datasourcePass);
        cpds.setAcquireRetryAttempts(3);
        cpds.setMaxPoolSize(100);
        cpds.setMinPoolSize(1);
        return cpds;
    }

    @Bean
    public BudgetComponent budgetComponent(){
        return new BudgetComponent();
    }
}
