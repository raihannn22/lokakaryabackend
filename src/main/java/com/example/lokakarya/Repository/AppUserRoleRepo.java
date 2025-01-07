package com.example.lokakarya.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lokakarya.Entity.AppUserRole;
import java.util.List;
import java.util.UUID;

public interface AppUserRoleRepo extends JpaRepository<AppUserRole, UUID> {
    public List<AppUserRole> findByUserId(UUID id);
    public List<AppUserRole> deleteByUserId(UUID id);
}