package com.example.lokakarya.Repository;

import com.example.lokakarya.Entity.AppRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppRoleMenuRepo extends JpaRepository<AppRoleMenu, UUID> {
//
//    public List<AppRoleMenu> findByAppRoleId(UUID appRoleId);

}
