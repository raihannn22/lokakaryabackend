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
@Table(name = "TBL_APP_ROLE")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "ROLENAME", length = 30, nullable = false)
    private String roleName;

    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;

    @Column(name = "CREATED_BY",  nullable = false)
    private UUID createdBy;

    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;
}