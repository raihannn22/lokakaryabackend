package com.example.LokaKarya.Repository;

import com.example.LokaKarya.Entity.AppMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppMenuRepo extends JpaRepository<AppMenu, UUID> {
}
