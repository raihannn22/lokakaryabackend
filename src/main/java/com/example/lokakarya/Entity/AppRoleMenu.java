package com.example.lokakarya.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString
@Table(name = "TBL_APP_ROLE_MENU")
public class AppRoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private AppRole appRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ID",  nullable = false)
    private AppMenu appMenu;
}