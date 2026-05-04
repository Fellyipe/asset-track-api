package com.github.fellyipe.assettrackapi.domain.repository;

import com.github.fellyipe.assettrackapi.domain.model.MaintenanceOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MaintenanceOrderRepository extends JpaRepository<MaintenanceOrder, UUID> {
    List<MaintenanceOrder> findByTechnicianId(UUID id);
    List<MaintenanceOrder> findByAssetDepartmentId(UUID id);
}
