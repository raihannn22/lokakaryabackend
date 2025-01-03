package com.example.lokakarya.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username); // Cek username unik
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByEmailOrUsername(String email, String username);
    User findByUsername(String username);

    List<User> findByDivisionId(UUID divisionId);

    // List<User> findByDivisionIdAndRoleId(UUID divisionId, UUID roleId);

    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.username) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR " +
           "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR " +
           "LOWER(u.position) LIKE LOWER(CONCAT('%', :searchKeyword, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :searchKeyword, '%'))")
    Page<User> searchUsers(@Param("searchKeyword") String searchKeyword, Pageable pageable);
    
}

