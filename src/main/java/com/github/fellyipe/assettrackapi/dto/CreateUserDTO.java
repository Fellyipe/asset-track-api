package com.github.fellyipe.assettrackapi.dto;

import com.github.fellyipe.assettrackapi.domain.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateUserDTO(
    @NotBlank
    String name,
    @NotBlank
    String email,
    @NotBlank
    String password,
    @NotNull
    Role role,
    UUID departmentId
) {
}
