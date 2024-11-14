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
@Table(name = "TBL_ATTITUDE_SKILL")
public class tbl_attitude_skill {
    @Id
    @Column(name = "ID", length = 32, nullable = false)
    private UUID id;

    @Column(name = "ATTITUDE_SKILL", length = 100, nullable = false)
    private String attitudeSkill;

    @Column(name = "GROUP_ID", length = 32, nullable = false)
    private String groupId;

    @Column(name = "ENABLED", length = 1)
    private Integer enabled = 1;

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
