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
@Table(name = "TBL_APP_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "USERNAME", length = 100, nullable = false)
    private String username;

    @Column(name = "FULL_NAME", length = 100, nullable = false)
    private String fullName;

    @Column(name = "POSITION", length = 50)
    private String position;

    @Column(name = "EMAIL_ADDRESS", length = 100)
    private String email;

    @Column(name = "EMPLOYEE_STATUS", nullable = false)
    private int employeeStatus; // 1: kontrak, 2: permanen

    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column(name = "ENABLED")
    private Integer enabled = 1;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "ROLE_ID")
    private UUID roleId;

    @Column(name = "DIVISION_ID")
    private UUID divisionId;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private UUID createdBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;
}
