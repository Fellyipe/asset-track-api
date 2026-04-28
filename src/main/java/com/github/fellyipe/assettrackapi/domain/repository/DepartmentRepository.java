package com.github.fellyipe.assettrackapi.domain.repository;

import com.github.fellyipe.assettrackapi.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
