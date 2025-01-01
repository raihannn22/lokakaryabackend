package com.example.lokakarya.Entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "YEAR", nullable = false)
    private Integer year;

    @Column(name = "SCORE",length = 3, nullable = false)
    private Double score;

    @Column(name = "STATUS", nullable = false)
    private Integer status = 1;

    @Column(name = "CREATED_BY", nullable = false)
    private UUID createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

}