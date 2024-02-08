package com.customer.customerservice.commons;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

/**
 * Author: NUWAN
 * Date: 2024-02-08
 * Description:
 * validate given value
 */
public class UUIDValidator implements ConstraintValidator<ValidUUID, String> {
    @Override
    public void initialize(ValidUUID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are allowed
        }
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
