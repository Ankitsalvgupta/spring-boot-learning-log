package com.ankit.employees.employeeDepartment.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.val;

public class DepartmentTitleValidator implements ConstraintValidator<DepartmentTitleValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(value.length() >= 2 && value.length()< 30)
            return true;
        return false;
    }
}
