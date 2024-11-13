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
//@Table(name = "TBL_DIVISION")
//public class tbl_division {
//    @Id
//    @Column(name = "ID", length = 32, nullable = false)
//    private UUID id;
//
//    @Column(name = "DIVISION_NAME", length = 100, nullable = false)
//    private String divisionName;
//
//    @Column(name = "CREATED_BY", length = 32)
//    private UUID createdBy;
//
//    @Column(name = "CREATED_AT", nullable = false)
//    @Temporal(TemporalType.DATE)
//    private Date createdAt;
//
//    @Column(name = "UPDATED_BY", length = 32)
//    private UUID updatedBy;
//
//    @Column(name = "UPDATED_AT")
//    @Temporal(TemporalType.DATE)
//    private Date updatedAt;
//}
