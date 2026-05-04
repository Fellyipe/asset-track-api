package com.github.fellyipe.assettrackapi.service;

import com.github.fellyipe.assettrackapi.domain.model.Department;
import com.github.fellyipe.assettrackapi.domain.repository.DepartmentRepository;
import com.github.fellyipe.assettrackapi.dto.CreateDepartmentDTO;
import com.github.fellyipe.assettrackapi.dto.UpdateDepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(UUID id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department create(CreateDepartmentDTO dto) {

        Department department = new Department();
        department.setName(dto.name());

        return departmentRepository.save(department);
    }

    public Department update(UUID id, UpdateDepartmentDTO dto) {
        Department department = departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department not found"));

        if(dto.name() != null && !dto.name().isBlank()) department.setName(dto.name());

        return departmentRepository.save(department);
    }

    public void delete(UUID id) {
        if (!departmentRepository.existsById(id)) {
            throw new RuntimeException("Department not found");
        }
        departmentRepository.deleteById(id);
    }

}
