package com.github.fellyipe.assettrackapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateMaintenanceOrderDTO(
    @NotNull
    LocalDateTime scheduledDate,
    @NotNull
    UUID assetId,
    @NotNull
    UUID technicianId,
    @NotBlank
    String description,
    String notes
) {
}

