package com.ankit.employees.employeeDepartment.dto;

import com.ankit.employees.employeeDepartment.annotations.DepartmentTitleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Name of the department cannot be blank")
    @DepartmentTitleValidation
    private String title;
    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private boolean isActive;
    @PastOrPresent(message = "Department Created field in Department cannot be in the future")
    private LocalDateTime createdAt;
}
