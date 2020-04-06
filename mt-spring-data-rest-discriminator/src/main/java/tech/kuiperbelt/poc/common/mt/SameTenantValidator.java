package tech.kuiperbelt.poc.common.mt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tech.kuiperbelt.poc.common.BaseEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Slf4j
public class SameTenantValidator implements ConstraintValidator<SameTenantCheck, BaseEntity> {

    @Autowired
    private TenantContext tenantContext;


    private SameTenantCheck check;

    @Override
    public void initialize(SameTenantCheck constraintAnnotation) {
        this.check = constraintAnnotation;

    }

    @Override
    public boolean isValid(BaseEntity entity, ConstraintValidatorContext constraintValidatorContext) {
        boolean sameTenant = Objects.equals(entity.getTenantId(), tenantContext.getTenantId());
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Different tenant " + entity.getTenantId() + ", it should keep as same as " + tenantContext.getTenantId())
                .addConstraintViolation();
        return sameTenant;
    }
}
