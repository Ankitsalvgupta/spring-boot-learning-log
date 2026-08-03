package com.ankit.employees.employeeDepartment.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = {DepartmentTitleValidator.class})
public @interface DepartmentTitleValidation {

    String message() default "Number of characters in title should be in the range: [2, 30]";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
