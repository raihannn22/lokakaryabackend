package com.example.LokaKarya.Repository;

import com.example.LokaKarya.Entity.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppUserRoleRepo extends JpaRepository<AppUserRole, UUID> {
}
