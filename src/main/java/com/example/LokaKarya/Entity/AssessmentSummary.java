package com.example.LokaKarya.Entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "TBL_ASSESSMENT_SUMMARY")
public class AssessmentSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "SCORE",length = 3, nullable = false)
    private Float score;

    @Column(name = "STATUS", nullable = false)
    private Integer status = 1;

    @Column(name = "CREATED_BY", nullable = false, length = 32)
    private UUID createdBy;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "UPDATED_BY", length = 32)
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

}
