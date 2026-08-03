package com.ankit.employees.employeeDepartment.repositories;

import com.ankit.employees.employeeDepartment.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
