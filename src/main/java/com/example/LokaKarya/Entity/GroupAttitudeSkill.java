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
@Table(name = "TBL_GROUP_ATTITUDE_SKILL")
public class GroupAttitudeSkill {
    @Id
    @Column(name = "ID", length = 32, nullable = false)
    private UUID id;

    @Column(name = "GROUP_NAME", length = 100, nullable = false)
    private UUID groupName;

    @Column(name = "PERCENTAGE", length = 3, nullable = false)
    private Integer percentage = 1;

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
