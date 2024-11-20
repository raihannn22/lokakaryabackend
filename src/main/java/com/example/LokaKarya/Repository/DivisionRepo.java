package com.example.LokaKarya.Repository;

import com.example.LokaKarya.Entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DivisionRepo extends JpaRepository <Division, UUID> {
}
