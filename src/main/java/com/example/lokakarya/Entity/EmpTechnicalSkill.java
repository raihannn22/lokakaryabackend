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
@Table(name = "TBL_EMP_TECHNICAL_SKILL")
public class EmpTechnicalSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID",nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TECHNICAL_SKILL_ID", nullable = false)
    private TechnicalSkill technicalSkill;
    
    @Column(name = "CRITERIA", length = 100, nullable = false)
    private String criteria;

    @Column(name = "SCORE", length = 3, nullable = false)
    private Integer score;

    @Column(name = "ASSESSMENT_YEAR", length = 4, nullable = false)
    private Integer assessmentYear;

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
}
