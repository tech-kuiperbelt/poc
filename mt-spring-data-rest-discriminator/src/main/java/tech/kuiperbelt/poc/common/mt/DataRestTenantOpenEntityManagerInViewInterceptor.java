package tech.kuiperbelt.poc.common.mt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.support.JpaHelper;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Optional;

@Slf4j
public class DataRestTenantOpenEntityManagerInViewInterceptor implements WebRequestInterceptor {
    private TenantContext tenantContext;
    private EntityManagerFactory emf;
    private JpaHelper jpaHelper;

    public DataRestTenantOpenEntityManagerInViewInterceptor(EntityManagerFactory emf, JpaHelper jpaHelper, TenantContext tenantContext) {
        this.jpaHelper = jpaHelper;
        this.emf = emf;
        this.tenantContext = tenantContext;
    }

    @PostConstruct
    public void init() {
        // register self to interceptors which used by SpringDataRest
        jpaHelper.getInterceptors().add(this);
    }

    @Override
    public void preHandle(WebRequest request)  {
        EntityManagerHolder emHolde = (EntityManagerHolder) TransactionSynchronizationManager.getResource(emf);
        Assert.notNull(emHolde, "EntityManagerHolder can not be null");
        EntityManager entityManager = emHolde.getEntityManager();
        org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
        Long tenantId = Optional.ofNullable(request.getHeader("TENANT_ID")).map(Long::valueOf).orElse(0L);
        session.enableFilter("tenantIdFilter").setParameter("incomeTenantId", tenantId);
        tenantContext.setTenantId(tenantId);
        log.info("Current Session enable tenantIdFilter, tenant id: {}", tenantId);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}
