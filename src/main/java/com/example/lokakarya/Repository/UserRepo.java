package com.example.lokakarya.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.lokakarya.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    boolean existsByUsername(String username); // Cek username unik
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByEmailOrUsername(String email, String username);
    User findByUsername(String username);

}

