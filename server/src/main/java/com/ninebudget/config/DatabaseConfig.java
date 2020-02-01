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
    private static final String DATASOURCE_CONTEXT = "java:/BudgetDS";
    private static final String PERSISTENCE_NAME = "BudgetDS";
    private static final String DEFAULT_SCHEMA = "ninebudget";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        //Add package to scan for entities.
        factory.setPackagesToScan(Account.class.getPackage().getName());
        factory.setPersistenceUnitName(PERSISTENCE_NAME);
        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.ddl-auto", "update");
        props.put("hibernate.default_schema", DEFAULT_SCHEMA);
        props.put("hibernate.cache.use_second_level_cache", "true");
        props.put("hibernate.cache.ehcache.missing_cache_strategy", "create");
        props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
        props.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
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
