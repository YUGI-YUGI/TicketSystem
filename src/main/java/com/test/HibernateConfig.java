package com.test;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@Configuration        
public class HibernateConfig {

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
         factory.setEntityManagerFactory(emf);
         return factory;
    }
}