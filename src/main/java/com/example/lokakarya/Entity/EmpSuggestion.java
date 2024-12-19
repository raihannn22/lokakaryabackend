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
@Table(name = "TBL_EMP_SUGGESTION")
public class EmpSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID",nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    
    // @Column(name = "USER_ID", length = 32, nullable = false)
    // private UUID userId;

    @Column(name = "SUGGESTION", length = 200)
    private String suggestion;

    @Column(name = "ASSESSMENT_YEAR", length = 4)
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
