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
@Table(name = "TBL_APP_MENU")
public class AppMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "MENU_NAME", nullable = false)
    private String menuName;

    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt  = new Date(System.currentTimeMillis());

    @Column(name = "CREATED_BY")
    private UUID createdBy;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
}
