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
@Table(name = "TBL_EMP_DEV_PLAN")
public class EmpDevPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "DEV_PLAN_ID", nullable = false)
    private DevPlan devPlan;

    @Column(name = "ASSESSMENT_YEAR", nullable = false)
    private Integer assessmentYear;

    @Column(name = "DETAIL", nullable = false)
    private String detail;

    @Column(name = "CREATED_BY")
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