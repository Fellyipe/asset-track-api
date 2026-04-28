package com.github.fellyipe.assettrackapi.service;

import com.github.fellyipe.assettrackapi.domain.model.Department;
import com.github.fellyipe.assettrackapi.domain.repository.DepartmentRepository;
import com.github.fellyipe.assettrackapi.dto.CreateDepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department create(CreateDepartmentDTO dto) {

        Department department = new Department();
        department.setName(dto.name());

        return departmentRepository.save(department);
    }

}
