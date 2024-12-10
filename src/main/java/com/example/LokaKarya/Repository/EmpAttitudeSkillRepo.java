package com.example.LokaKarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LokaKarya.Entity.EmpAttitudeSkill;

@Repository
public interface EmpAttitudeSkillRepo extends JpaRepository<EmpAttitudeSkill, UUID> {
    List<EmpAttitudeSkill> findByUserId(UUID userId);
    // List<EmpAttitudeSkill> findByUserIdandAssesmentYear(UUID userId, Integer assessmentYear);
    List<EmpAttitudeSkill> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);

}
