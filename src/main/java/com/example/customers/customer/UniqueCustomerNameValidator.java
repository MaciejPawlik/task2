package com.example.customers.customer;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueCustomerNameValidator implements ConstraintValidator<UniqueCustomerName, String> {

    private final CustomerService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service.getCustomerByName(value.trim()).isEmpty();
    }
}