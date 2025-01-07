package com.example.lokakarya.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.AppRoleMenu;
import java.util.UUID;

@Repository
public interface AppRoleMenuRepo extends JpaRepository<AppRoleMenu, UUID> {
}