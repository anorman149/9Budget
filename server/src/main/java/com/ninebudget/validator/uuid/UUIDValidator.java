package com.ninebudget.validator.uuid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UUIDValidator implements ConstraintValidator<ValidUUID, UUID> {
    private final Logger log = LoggerFactory.getLogger(UUIDValidator.class);

    @Override
    public void initialize(ValidUUID validUuid) { }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext cxt) {
        try{
            UUID.fromString(uuid.toString());
        }catch(Exception e){
            log.error("Invalid UUID...");
            return false;
        }

        return true;
    }
}