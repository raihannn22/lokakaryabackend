package com.example.LokaKarya.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "TBL_DIVISION")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "DIVISION_NAME", length = 100, nullable = false)
    private String divisionName;

    @Column(name = "CREATED_BY", nullable = false)
    private UUID createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date(System.currentTimeMillis());

    @Column(name = "UPDATED_BY",nullable = false)
    private UUID updatedBy;

    @Column(name = "UPDATED_AT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}