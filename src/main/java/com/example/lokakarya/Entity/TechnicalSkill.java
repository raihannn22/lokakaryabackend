package com.example.lokakarya.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "TBL_TECHNICAL_SKILL")
public class TechnicalSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID",nullable = false)
    private UUID id;

    @Column(name = "TECHNICAL_SKILL", length = 200, nullable = false)
    private String technicalSkill;

    @Column(name = "ENABLED", length = 1)
    private Integer enabled = 1;

    @Column(name = "CREATED_BY")
    private UUID createdBy;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @OneToMany(mappedBy = "technicalSkill", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EmpTechnicalSkill> empTechnicalSkills;
    
}
