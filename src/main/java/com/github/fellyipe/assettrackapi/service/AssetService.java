package com.github.fellyipe.assettrackapi.service;

import com.github.fellyipe.assettrackapi.domain.model.Asset;
import com.github.fellyipe.assettrackapi.domain.model.AssetStatus;
import com.github.fellyipe.assettrackapi.domain.model.Department;
import com.github.fellyipe.assettrackapi.domain.repository.AssetRepository;
import com.github.fellyipe.assettrackapi.domain.repository.DepartmentRepository;
import com.github.fellyipe.assettrackapi.dto.CreateAssetDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final DepartmentRepository departmentRepository;

    public AssetService(AssetRepository assetRepository, DepartmentRepository departmentRepository) {
        this.assetRepository = assetRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    public Asset create(CreateAssetDTO dto) {

        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Asset asset = new Asset();
        asset.setName(dto.name());
        asset.setDescription(dto.description());
        asset.setDepartment(department);
        asset.setStatus(AssetStatus.ACTIVE);

        return assetRepository.save(asset);
    }

}
