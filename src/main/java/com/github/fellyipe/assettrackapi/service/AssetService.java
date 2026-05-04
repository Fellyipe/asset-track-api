package com.github.fellyipe.assettrackapi.service;

import com.github.fellyipe.assettrackapi.domain.model.Asset;
import com.github.fellyipe.assettrackapi.domain.model.AssetStatus;
import com.github.fellyipe.assettrackapi.domain.model.Department;
import com.github.fellyipe.assettrackapi.domain.repository.AssetRepository;
import com.github.fellyipe.assettrackapi.domain.repository.DepartmentRepository;
import com.github.fellyipe.assettrackapi.dto.CreateAssetDTO;
import com.github.fellyipe.assettrackapi.dto.UpdateAssetDTO;
import com.github.fellyipe.assettrackapi.dto.UpdateDepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Asset findById(UUID id) {
        return assetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asset not found"));
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

    public Asset update(UUID id, UpdateAssetDTO dto) {
        Asset asset = assetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asset not found"));

        if(dto.departmentId() != null) {
            Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
            asset.setDepartment(department);
        }

        if(dto.name() != null && !dto.name().isBlank()) asset.setName(dto.name());
        if(dto.description() != null && !dto.description().isBlank()) asset.setDescription(dto.description());
        if(dto.status() != null) asset.setStatus(dto.status());

        return assetRepository.save(asset);
    }

    public void delete(UUID id) {
        if (!assetRepository.existsById(id)) {
            throw new RuntimeException("Asset not found");
        }
        assetRepository.deleteById(id);
    }

}
