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
@Table(name = "TBL_EMP_ATTITUDE_SKILL")
public class EmpAttitudeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID",nullable = false)
    private UUID id;

    @Column(name = "USER_ID", length = 32, nullable = false)
    private UUID userId;

    // @Column(name = "ATTITUDE_SKILL_ID", length = 32, nullable = false)
    // private UUID attitudeSkillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTITUDE_SKILL_ID", nullable = false)
    private AttitudeSkill attitudeSkill;

    @Column(name = "SCORE", length = 3, nullable = false)
    private Integer score;

    @Column(name = "ASSESSMENT_YEAR", length = 4, nullable = false)
    private Integer assessmentYear;
    
    @Column(name = "CREATED_BY", length = 32)
    private UUID createdBy;
    
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt = new Date(System.currentTimeMillis());

    @Column(name = "UPDATED_BY", length = 32)
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    
}
