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
@Table(name = "TBL_ACHIEVEMENT")
public class tbl_achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Menghasilkan UUID secara otomatis
    @Column(name = "ID", length = 32, nullable = false)
    private UUID id;

    @Column(name = "ACHIEVEMENT", length = 100, nullable = false)
    private String achievement;

    @Column(name = "GROUP_ID", length = 32, nullable = false)
    private UUID groupId;

    @Column(name = "ENABLED", length = 1)
    private Integer enabled = 1;

    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "CREATED_BY", length = 32)
    private UUID createdBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "UPDATED_BY", length = 32)
    private UUID updatedBy;

    
}
