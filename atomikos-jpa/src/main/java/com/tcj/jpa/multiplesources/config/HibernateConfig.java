package com.tcj.jpa.multiplesources.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;

@Configuration
@DependsOn("transactionManagerFactory")
public class HibernateConfig  {

    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;


    /**
     *  返回hibernate配置 包含在yml里配置的配置
     * @return
     */
    @Bean(name = "hibernatePropertie")
    public Map getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
    }
}
