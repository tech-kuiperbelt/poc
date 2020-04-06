package tech.kuiperbelt.poc.domain;

import lombok.Getter;
import lombok.Setter;
import tech.kuiperbelt.poc.common.BaseEntity;
import tech.kuiperbelt.poc.common.mt.SameTenantCheck;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Bar extends BaseEntity {

    private String name;

    @SameTenantCheck
    @OneToOne
    private Foo foo;
}
