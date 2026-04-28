package com.github.fellyipe.assettrackapi.domain.repository;

import com.github.fellyipe.assettrackapi.domain.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssetRepository extends JpaRepository<Asset, UUID> {
}
