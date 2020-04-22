package tech.kuiperbelt.poc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.envers.Audited;
import tech.kuiperbelt.lib.ems.EmsEntity;

import javax.persistence.Entity;

@FieldNameConstants
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Audited
@Entity
@Getter
@Setter
public class Foo extends EmsEntity {
    private String name;
}
