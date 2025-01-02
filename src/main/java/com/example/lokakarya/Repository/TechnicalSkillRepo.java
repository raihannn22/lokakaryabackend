package com.example.lokakarya.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.TechnicalSkill;

@Repository
public interface TechnicalSkillRepo extends JpaRepository<TechnicalSkill, UUID> {

    Page<TechnicalSkill> findByTechnicalSkillContainingIgnoreCase(String technicalSkill, Pageable pageable);
    
    long countByTechnicalSkillContainingIgnoreCase(String technicalSkill);
}
