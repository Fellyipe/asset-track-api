package com.github.fellyipe.assettrackapi.controller;

import com.github.fellyipe.assettrackapi.domain.model.Asset;
import com.github.fellyipe.assettrackapi.domain.model.Asset;
import com.github.fellyipe.assettrackapi.dto.CreateAssetDTO;
import com.github.fellyipe.assettrackapi.dto.UpdateAssetDTO;
import com.github.fellyipe.assettrackapi.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> findAll() {
        return ResponseEntity.ok(assetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(assetService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody @Valid CreateAssetDTO dto) {
        Asset asset = assetService.create(dto);
        return ResponseEntity.ok(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> update(@PathVariable UUID id, @RequestBody @Valid UpdateAssetDTO dto) {
        Asset asset = assetService.update(id, dto);
        return ResponseEntity.ok(asset);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        assetService.delete(id);
        return ResponseEntity.notFound().build();
    }
}
