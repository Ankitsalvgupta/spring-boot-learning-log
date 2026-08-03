package com.ankit.employees.employeeDepartment.controller;

import com.ankit.employees.employeeDepartment.dto.DepartmentDTO;
import com.ankit.employees.employeeDepartment.exceptions.ResourceNotFoundException;
import com.ankit.employees.employeeDepartment.services.DepartmentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> departmentDTO = departmentService.getDepartmentById(id);
        return departmentDTO
                .map(departmentDTO1 -> ResponseEntity.ok(departmentDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
    @PostMapping("/")
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO savedDepartment = departmentService.createNewDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO,
                                          @PathVariable Long id) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long id) {
        boolean gotDeleted = departmentService.deleteDepartmentById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }
}
