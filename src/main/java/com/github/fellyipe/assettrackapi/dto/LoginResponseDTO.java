package com.github.fellyipe.assettrackapi.dto;

import com.github.fellyipe.assettrackapi.domain.model.User;

public record LoginResponseDTO(String token, User user) {
}
