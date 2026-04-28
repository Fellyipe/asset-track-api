package com.github.fellyipe.assettrackapi.controller;

import com.github.fellyipe.assettrackapi.domain.model.Department;
import com.github.fellyipe.assettrackapi.dto.CreateDepartmentDTO;
import com.github.fellyipe.assettrackapi.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody @Valid CreateDepartmentDTO dto) {
        Department department = departmentService.create(dto);
        return ResponseEntity.ok(department);
    }
}
