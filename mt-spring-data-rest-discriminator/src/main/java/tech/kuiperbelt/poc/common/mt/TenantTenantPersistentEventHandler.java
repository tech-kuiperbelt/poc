package tech.kuiperbelt.poc.common.mt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Slf4j
@Service
@RepositoryEventHandler
public class TenantTenantPersistentEventHandler {

    @Autowired
    private TenantContext tenantContext;

    @HandleBeforeCreate
    public void beforeCreate(TenantEntity entity) {
        log.info("Setting tenant id to entity");
        Assert.isNull(entity.getTenantId(), "tenant id error");
        entity.setTenantId(tenantContext.getTenantId());
    }

    @HandleBeforeSave
    public void beforeUpdate(TenantEntity entity) {
        Assert.isTrue(Objects.equals(entity.getTenantId(), tenantContext.getTenantId()), "tenant id error");
    }

    @HandleBeforeDelete
    public void beforeDelete(TenantEntity entity) {
        Assert.isTrue(Objects.equals(entity.getTenantId(), tenantContext.getTenantId()), "tenant id error");
    }



    @HandleBeforeLinkSave
    public void beforeLinkCreate(TenantEntity sourceEntity, TenantEntity linkedEntity) {
        Assert.isTrue(Objects.equals(sourceEntity.getTenantId(), tenantContext.getTenantId()), "tenant id error");
        Assert.isTrue(Objects.equals(linkedEntity.getTenantId(), tenantContext.getTenantId()), "tenant id error");
    }
}
