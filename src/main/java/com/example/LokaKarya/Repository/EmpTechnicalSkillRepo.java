package com.example.LokaKarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LokaKarya.Entity.EmpTechnicalSkill;

@Repository
public interface EmpTechnicalSkillRepo extends JpaRepository<EmpTechnicalSkill, UUID> {
    List<EmpTechnicalSkill> findByUserId(UUID userId);
    List<EmpTechnicalSkill> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);

}
