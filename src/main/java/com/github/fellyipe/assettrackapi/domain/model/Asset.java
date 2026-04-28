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
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private AssetStatus status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
