package com.example.lokakarya.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.AppRole;
import java.util.UUID;

@Repository
public interface AppRoleRepo extends JpaRepository<AppRole, UUID> {
}