package com.example.LokaKarya.Entity;

import jakarta.persistence.*;
import lombok.*;


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
    private java.util.Date createdAt;

    @Column(name = "CREATED_BY", nullable = false)
    private UUID createdBy;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

    @Column(name = "UPDATED_AT")
    private java.util.Date updatedAt;
}
