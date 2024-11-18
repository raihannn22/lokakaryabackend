package com.example.LokaKarya.Entity;

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
@Table(name = "TBL_EMP_ACHIEVEMENT_SKILL")
public class EmpAchievementSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID",nullable = false)
    private UUID id;

    @Column(name = "USER_ID", length = 32, nullable = false)
    private UUID userId;

    @Column(name = "NOTES", length = 100, nullable = false)
    private String notes;

    @Column(name = "ACHIEVEMENT_ID", length = 32, nullable = false)
    private UUID achievementId;

    @Column(name = "SCORE", length = 3, nullable = false)
    private Integer score;

    @Column(name = "ASSESSMENT_YEAR", length = 4, nullable = false)
    private Integer assessmentYear;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private UUID createdBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

}
    
