package com.example.customers.customer;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCustomerNameValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCustomerName {
    String message() default "Customer with this name exists already";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
