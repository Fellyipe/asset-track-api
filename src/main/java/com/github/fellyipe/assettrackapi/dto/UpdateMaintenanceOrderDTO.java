package com.github.fellyipe.assettrackapi.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateMaintenanceOrderDTO(
    LocalDateTime scheduledDate,
    UUID technicianId,
    String description,
    String notes
) {
}

