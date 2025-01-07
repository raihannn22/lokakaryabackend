package com.example.lokakarya.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "TBL_DEV_PLAN")
public class DevPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    @Column(name = "ID",nullable = false)
    private UUID id;

    @Column(name = "PLAN", length = 100, nullable = false)
    private String plan;

    @Column(name = "ENABLED", nullable = false)
    private Integer enabled = 1;

    @Column(name = "CREATED_BY", nullable = false)
    private UUID createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}