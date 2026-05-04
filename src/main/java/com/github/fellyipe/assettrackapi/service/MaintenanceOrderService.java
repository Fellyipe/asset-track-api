package com.github.fellyipe.assettrackapi.service;

import com.github.fellyipe.assettrackapi.domain.model.Asset;
import com.github.fellyipe.assettrackapi.domain.model.MaintenanceOrder;
import com.github.fellyipe.assettrackapi.domain.model.User;
import com.github.fellyipe.assettrackapi.domain.repository.AssetRepository;
import com.github.fellyipe.assettrackapi.domain.repository.MaintenanceOrderRepository;
import com.github.fellyipe.assettrackapi.domain.repository.UserRepository;
import com.github.fellyipe.assettrackapi.dto.CreateMaintenanceOrderDTO;
import com.github.fellyipe.assettrackapi.dto.UpdateMaintenanceOrderDTO;
import com.github.fellyipe.assettrackapi.security.CustomUserDetails;
import com.github.fellyipe.assettrackapi.security.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MaintenanceOrderService {

    private final MaintenanceOrderRepository maintenanceOrderRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    private final SecurityUtils securityUtils;

    public MaintenanceOrderService(MaintenanceOrderRepository maintenanceOrderRepository, AssetRepository assetRepository, UserRepository userRepository, SecurityUtils securityUtils) {
        this.maintenanceOrderRepository = maintenanceOrderRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
        this.securityUtils = securityUtils;
    }

    public List<MaintenanceOrder> findAll() {
        return maintenanceOrderRepository.findAll();
    }

    public MaintenanceOrder findById(UUID id) {
        return maintenanceOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Maintenance Order not found"));
    }

    public MaintenanceOrder create(CreateMaintenanceOrderDTO dto) {

        Asset asset = assetRepository.findById(dto.assetId())
            .orElseThrow(() -> new RuntimeException("Asset not found"));
        User technician = userRepository.findById(dto.technicianId())
            .orElseThrow(() -> new RuntimeException("Technician not found"));

        MaintenanceOrder maintenanceOrder = new MaintenanceOrder();
        maintenanceOrder.setAsset(asset);
        maintenanceOrder.setTechnician(technician);
        maintenanceOrder.setScheduledDate(dto.scheduledDate());
        maintenanceOrder.setDescription(dto.description());
        maintenanceOrder.setNotes(dto.notes());

        return maintenanceOrderRepository.save(maintenanceOrder);
    }

    public MaintenanceOrder update(UUID id, UpdateMaintenanceOrderDTO dto) {
        MaintenanceOrder maintenanceOrder = maintenanceOrderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Maintenance Order not found"));

        if(dto.technicianId() != null) {
            User technician = userRepository.findById(dto.technicianId())
                    .orElseThrow(() -> new RuntimeException("Technician not found"));
            maintenanceOrder.setTechnician(technician);
        }

        if(dto.scheduledDate() != null) maintenanceOrder.setScheduledDate(dto.scheduledDate());
        if(dto.description() != null && !dto.description().isBlank()) maintenanceOrder.setDescription(dto.description());
        if(dto.notes() != null && !dto.notes().isBlank()) maintenanceOrder.setNotes(dto.notes());

        return maintenanceOrderRepository.save(maintenanceOrder);
    }

    public void delete(UUID id) {
        if (!maintenanceOrderRepository.existsById(id)) {
            throw new RuntimeException("Maintenance Order not found");
        }
        maintenanceOrderRepository.deleteById(id);
    }

    public List<MaintenanceOrder> findForCurrentUser() {
        User user = securityUtils.getCurrentUser();

        return switch(user.getRole()) {
            case ADMIN -> maintenanceOrderRepository.findAll();
            case MANAGER -> maintenanceOrderRepository.findByAssetDepartmentId(user.getDepartment().getId());
            case TECHNICIAN -> maintenanceOrderRepository.findByTechnicianId(user.getId());
            default -> throw new RuntimeException("Unauthorized role");
        };
    }

}
