package com.example.LokaKarya.Controller;


import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "TBL_APP_USER")
public class tbl_app_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", length = 32, nullable = false)
    private String idl;

    @Column(name = "USERNAME", length = 100, nullable = false)
    private String username;

    @Column(name = "FULL_NAME", length = 100, nullable = false)
    private String fullName;

    @Column(name = "POSITION", length = 50)
    private String position;

    @Column(name = "EMAIL_ADDRESS", length = 100)
    private String email;

    @Column(name = "JOIN_DATE", columnDefinition = "NUMBER(1) DEFAULT 0")
    private Date joinDate;

    @Column(name = "ENABLED", columnDefinition = "NUMBER(1) DEFAULT 1")
    private Integer enabled = 1;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "ROLE_ID", length = 32)
    private String roleId;

    @Column(name = "DIVISION_ID", length = 32)
    private String divisionId;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "CREATED_BY", length = 32)
    private String createdBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @Column(name = "UPDATED_BY", length = 32)
    private String updatedBy;
}
