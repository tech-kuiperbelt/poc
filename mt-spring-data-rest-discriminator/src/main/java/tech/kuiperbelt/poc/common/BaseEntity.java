package tech.kuiperbelt.poc.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import tech.kuiperbelt.poc.common.mt.TenantEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@FilterDef(
        name = "tenantIdFilter",
        parameters = @ParamDef(name = "incomeTenantId", type = "long")
)
@Filter(
        name = "tenantIdFilter",
        condition = "tenant_id = :incomeTenantId"
)
public abstract class BaseEntity implements TenantEntity {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    private Long tenantId;
}
