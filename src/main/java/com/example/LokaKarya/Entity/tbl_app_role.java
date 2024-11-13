//package com.example.LokaKarya.Entity;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.sql.Date;
//import java.util.UUID;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//@Entity
//@ToString
//@Table(name = "TBL_APP_ROLE")
//public class tbl_app_role {
//    @Id
//    @Column(name = "ID", length = 32, nullable = false)
//    private UUID id;
//
//    @Column(name = "ROLENAME", length = 30, nullable = false)
//    private String roleName;
//
//    @Column(name = "CREATED_AT", nullable = false)
//    @Temporal(TemporalType.DATE)
//    private Date createdAt;
//
//    @Column(name = "CREATED_BY", length = 32)
//    private UUID createdBy;
//
//    @Column(name = "UPDATED_AT")
//    @Temporal(TemporalType.DATE)
//    private Date updatedAt;
//
//    @Column(name = "UPDATED_BY", length = 32)
//    private UUID updatedBy;
//}
