package com.example.lokakarya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.DevPlan;

import java.util.UUID;

@Repository
public interface DevPlanRepo extends JpaRepository<DevPlan, UUID> {
}

