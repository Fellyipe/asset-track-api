package com.github.fellyipe.assettrackapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenance_orders")
public class MaintenanceOrder {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime scheduledDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    private String description;

    private  String notes;
}
