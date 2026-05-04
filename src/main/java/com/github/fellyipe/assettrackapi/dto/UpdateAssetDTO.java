package com.github.fellyipe.assettrackapi.dto;

import com.github.fellyipe.assettrackapi.domain.model.AssetStatus;

import java.util.UUID;

public record UpdateAssetDTO(
    String name,
    String description,
    AssetStatus status,
    UUID departmentId)
{ }