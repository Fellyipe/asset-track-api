package com.github.fellyipe.assettrackapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDepartmentDTO(
    @NotBlank
    String name)
{ }