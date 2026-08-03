package com.ankit.employees.employeeDepartment.services;

import com.ankit.employees.employeeDepartment.dto.DepartmentDTO;
import com.ankit.employees.employeeDepartment.entities.DepartmentEntity;
import com.ankit.employees.employeeDepartment.exceptions.ResourceNotFoundException;
import com.ankit.employees.employeeDepartment.repositories.DepartmentRepository;
import jakarta.validation.Valid;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id).map(departmentEntity -> mapper.map(departmentEntity, DepartmentDTO.class));
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity -> mapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(@Valid DepartmentDTO departmentDTO) {
        DepartmentEntity toSaveEntity = mapper.map(departmentDTO, DepartmentEntity.class);
        DepartmentEntity savedEntity = departmentRepository.save(toSaveEntity);
        return mapper.map(savedEntity, DepartmentDTO.class);
    }

    public void isExistsByDepartmentId(Long id) {
        boolean exists = departmentRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Department not found for id: " + id);
    }
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO departmentDTO) {
        isExistsByDepartmentId(id);
        DepartmentEntity departmentEntity = mapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(id);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return mapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(Long id) {
        isExistsByDepartmentId(id);
        departmentRepository.deleteById(id);
        return true;
    }
}
