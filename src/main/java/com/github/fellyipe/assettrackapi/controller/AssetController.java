package com.github.fellyipe.assettrackapi.controller;

import com.github.fellyipe.assettrackapi.domain.model.Asset;
import com.github.fellyipe.assettrackapi.dto.CreateAssetDTO;
import com.github.fellyipe.assettrackapi.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<Asset> create(@RequestBody @Valid CreateAssetDTO dto) {
        Asset asset = assetService.create(dto);
        return ResponseEntity.ok(asset);
    }
}
