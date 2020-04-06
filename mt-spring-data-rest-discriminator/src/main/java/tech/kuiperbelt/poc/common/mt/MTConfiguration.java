package tech.kuiperbelt.poc.common.mt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.rest.webmvc.support.JpaHelper;
import org.springframework.validation.Validator;

import javax.persistence.EntityManagerFactory;

/**
 * It works instead of JpaWebConfiguration
 * see JpaBaseConfiguration.JpaWebConfiguration
 */
@Configuration
public class MTConfiguration {

    @Autowired
    private Validator validator;

    @Bean
    public DataRestTenantOpenEntityManagerInViewInterceptor dataRestTenantOpenEntityManagerInViewInterceptor(
            EntityManagerFactory entityManagerFactory, JpaHelper jpaHelper){
        DataRestTenantOpenEntityManagerInViewInterceptor dataRestTenantOpenEntityManagerInViewInterceptor =
                new DataRestTenantOpenEntityManagerInViewInterceptor(entityManagerFactory, jpaHelper, requestContext());
        return dataRestTenantOpenEntityManagerInViewInterceptor;
    }

    @Bean
    public TenantContext requestContext() {
        return new TenantContext();
    }


    // 将Spring Validator 配置到JPA中， 这样可以在自定义的Validator中用 Autowired 注入Spring Bean
    @Bean
    @Lazy
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return hibernateProperties -> {
            if(validator != null) {
                hibernateProperties.put("javax.persistence.validation.factory", validator);
            }
        };
    }

}
