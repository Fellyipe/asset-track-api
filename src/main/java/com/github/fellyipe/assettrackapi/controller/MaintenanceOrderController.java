package com.github.fellyipe.assettrackapi.controller;

import com.github.fellyipe.assettrackapi.domain.model.MaintenanceOrder;
import com.github.fellyipe.assettrackapi.dto.CreateMaintenanceOrderDTO;
import com.github.fellyipe.assettrackapi.dto.UpdateMaintenanceOrderDTO;
import com.github.fellyipe.assettrackapi.service.MaintenanceOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAnyRole('ADMIN','MANAGER','TECHNICIAN')")
@RequestMapping("/maintenance-orders")
public class MaintenanceOrderController {
    private final MaintenanceOrderService maintenanceOrderService;

    public MaintenanceOrderController(MaintenanceOrderService maintenanceOrderService) {
        this.maintenanceOrderService = maintenanceOrderService;
    }

//    @GetMapping
//    public ResponseEntity<List<MaintenanceOrder>> findAll() {
//        return ResponseEntity.ok(maintenanceOrderService.findAll());
//    }

    @GetMapping
    public ResponseEntity<List<MaintenanceOrder>> findForCurrentUser() {
        return ResponseEntity.ok(maintenanceOrderService.findForCurrentUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceOrder> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(maintenanceOrderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MaintenanceOrder> create(@RequestBody @Valid CreateMaintenanceOrderDTO dto) {
        MaintenanceOrder maintenanceOrder = maintenanceOrderService.create(dto);
        return ResponseEntity.ok(maintenanceOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaintenanceOrder> update(@PathVariable UUID id, @RequestBody @Valid UpdateMaintenanceOrderDTO dto) {
        MaintenanceOrder maintenanceOrder = maintenanceOrderService.update(id, dto);
        return ResponseEntity.ok(maintenanceOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        maintenanceOrderService.delete(id);
        return ResponseEntity.notFound().build();
    }

}
