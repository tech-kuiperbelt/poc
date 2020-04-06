package tech.kuiperbelt.poc.domain;

import lombok.Getter;
import lombok.Setter;
import tech.kuiperbelt.poc.common.BaseEntity;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Foo extends BaseEntity {

    private String name;
}
