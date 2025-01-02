package com.example.lokakarya.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.DevPlan;
import com.example.lokakarya.Entity.TechnicalSkill;

import java.util.UUID;

@Repository
public interface DevPlanRepo extends JpaRepository<DevPlan, UUID> {
    Page<DevPlan> findByPlanContainingIgnoreCase(String plan, Pageable pageable);
    
    long countByPlanContainingIgnoreCase(String plan);
}

