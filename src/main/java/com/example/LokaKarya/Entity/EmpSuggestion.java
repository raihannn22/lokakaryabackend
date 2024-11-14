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
@Table(name = "TBL_EMP_SUGGESTION")
public class EmpSuggestion {
    @Id
    @Column(name = "ID", length = 32, nullable = false)
    private UUID id;

    @Column(name = "USER_ID", length = 32, nullable = false)
    private UUID userId;

    @Column(name = "SUGGESTION", length = 200)
    private String suggestion;

    @Column(name = "ASSESSMENT_YEAR", length = 4)
    private Integer assessmentYear;

    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "CREATED_BY", length = 32)
    private UUID createdBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "UPDATED_BY", length = 32)
    private UUID updatedBy;

    
}
