package com.ninebudget.config;

import com.ninebudget.model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages={"com.ninebudget.model"})
@EnableJpaRepositories("com.ninebudget.repository")
@EnableTransactionManagement
public class DatabaseConfig {
    private static final String DATASOURCE_CONTEXT = "java:/DemoDS";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        //Add package to scan for entities.
        factory.setPackagesToScan(Account.class.getPackage().getName());
        factory.setPersistenceUnitName("DemoDS");
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.ddl-auto", "update");
        props.put("hibernate.default_schema", "demo123"); //TODO Change
        props.put("hibernate.show_sql", "true"); //TODO turn off
        props.put("hibernate.cache.use_second_level_cache", "true");
        props.put("hibernate.cache.ehcache.missing_cache_strategy", "create");
        props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        factory.setJpaProperties(props);
        factory.setDataSource((DataSource) new InitialContext().lookup(DATASOURCE_CONTEXT));
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
