// package com.example.LokaKarya.Entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.sql.Date;
// import java.util.UUID;

// @AllArgsConstructor
// @NoArgsConstructor
// @Builder
// @Data
// @Entity
// @ToString
// @Table(name = "TBL_APP_USER")
// public class AppUser {
//     @Id
//     // @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "ID", length = 32, nullable = false)
//     private UUID id;

//     @Column(name = "USERNAME", length = 30, nullable = false)
//     private String username;

//     @Column(name = "FULL_NAME", length = 60, nullable = false)
//     private String fullName;

//     @Column(name = "POSITION", length = 50, nullable = false)
//     private String position;

//     @Column(name = "EMAIL_ADDRESS", length = 60, nullable = false)
//     private String emailAddress;

//     @Column(name = "EMPLOYEE_STATUS", length = 1, nullable = false)
//     private Integer employeeStatus = 1;

//     @Column(name = "JOIN_DATE", nullable = false)
//     private Date joinDate;

//     @Column(name = "ENABLED", length = 1)
//     private Integer enabled = 1;

//     @Column(name = "PASSWORD", length = 100, nullable = false)
//     private String password;

//     // @Column(name = "ROLE_ID", length = 32, nullable = false)
//     // private UUID roleId;

//     @Column(name = "DIVISION_ID", length = 32, nullable = false)
//     private UUID divisionId;

//     @Column(name = "CREATED_AT", nullable = false)
//     @Temporal(TemporalType.DATE)
//     private Date createdAt;

//     @Column(name = "CREATED_BY", length = 32)
//     private UUID createdBy;

//     @Column(name = "UPDATED_AT")
//     @Temporal(TemporalType.DATE)
//     private Date updatedAt;

//     @Column(name = "UPDATED_BY", length = 32)
//     private UUID updatedBy;
// }
