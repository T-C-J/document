package com.tcj.jpa.multiplesources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@DependsOn({"transactionManagerFactory","hibernatePropertie"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactorySecondary",
        transactionManagerRef = "transactionManagerFactory",
        basePackages = {"com.tcj.jpa.multiplesources.repository.secondary"})
public class SecondaryConfig {

    @Autowired
    @Qualifier("hibernatePropertie")
    private Map hibernatePropertie;

    @Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;



    @Bean(name = "entityManagerFactorySecondary")
    @DependsOn("transactionManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager(EntityManagerFactoryBuilder builder) throws Throwable {
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean primaryPersistenceUnit = builder
                .dataSource(secondaryDataSource)
                .properties(hibernatePropertie)
                .packages("com.tcj.jpa.multiplesources.domain.secondary") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
        primaryPersistenceUnit.setJpaPropertyMap(properties);
        return primaryPersistenceUnit;
    }

}
