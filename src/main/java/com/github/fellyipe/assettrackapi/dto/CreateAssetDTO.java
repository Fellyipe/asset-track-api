package com.github.fellyipe.assettrackapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateAssetDTO(
    @NotBlank
    String name,
    String description,
    @NotNull
    UUID departmentId)
{ }