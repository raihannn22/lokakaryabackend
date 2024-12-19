package com.example.lokakarya.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.Division;

@Repository
public interface DivisionRepo extends JpaRepository <Division, UUID> {
}
