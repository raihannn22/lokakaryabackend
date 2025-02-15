package com.example.lokakarya.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.lokakarya.Entity.AppMenu;
import java.util.List;
import java.util.UUID;

public interface AppMenuRepo extends JpaRepository<AppMenu, UUID> {
    @Query(value = "SELECT am.id, am.menu_name, ar.rolename from tbl_app_menu as am join tbl_app_role_menu as arm ON am.id = arm.menu_id " +
            "JOIN tbl_app_role as ar on arm.role_id = ar.id " +
            "join tbl_app_user_role as aur on aur.role_id = ar.id " +
            "JOIN tbl_app_user as au on au.id = aur.user_id where au.id = :userId",
            nativeQuery = true)
    List<Object[]> getAppMenuByUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT am.id, am.menu_name, ar.rolename from tbl_app_menu as am join tbl_app_role_menu as arm ON am.id = arm.menu_id " +
            "JOIN tbl_app_role as ar on arm.role_id = ar.id " +
            "join tbl_app_user_role as aur on aur.role_id = ar.id " +
            "JOIN tbl_app_user as au on au.id = aur.user_id where au.username = :userId",
            nativeQuery = true)
    List<Object[]> getAppMenuByUsername(@Param("userId") String userId);
}