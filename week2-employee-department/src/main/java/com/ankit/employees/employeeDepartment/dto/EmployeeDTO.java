package com.ankit.employees.employeeDepartment.dto;

import com.ankit.employees.employeeDepartment.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    @Size(min = 3, max = 10, message = "No of characters in name should be at least 3 or max 10")
    private String name;

    @NotBlank(message = "Email of the emp cannot be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @Max(value = 80, message = "age of emp cannot be greater than 80")
    @Min(value = 18, message = "age of emp cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of the emp cannot be blank")
    @EmployeeRoleValidation
    private String role;
    private Double salary;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;
}
