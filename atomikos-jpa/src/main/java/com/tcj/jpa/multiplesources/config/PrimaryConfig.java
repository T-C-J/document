package com.tcj.jpa.multiplesources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@DependsOn({"transactionManagerFactory","hibernatePropertie"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManagerFactory",
        basePackages = {"com.tcj.jpa.multiplesources.repository.primary"})
public class PrimaryConfig {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("hibernatePropertie")
    private Map hibernatePropertie;


    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    @DependsOn("transactionManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManager(EntityManagerFactoryBuilder builder) throws Throwable {

        LocalContainerEntityManagerFactoryBean primaryPersistenceUnit = builder
                .dataSource(primaryDataSource)
                .properties(hibernatePropertie)
                .packages("com.tcj.jpa.multiplesources.domain.primary") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");
        primaryPersistenceUnit.setJpaPropertyMap(properties);
        return primaryPersistenceUnit;
    }
}
