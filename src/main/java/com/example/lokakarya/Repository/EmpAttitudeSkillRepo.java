package com.example.lokakarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.EmpAttitudeSkill;

@Repository
public interface EmpAttitudeSkillRepo extends JpaRepository<EmpAttitudeSkill, UUID> {
    List<EmpAttitudeSkill> findByUserId(UUID userId);
    List<EmpAttitudeSkill> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
    List<EmpAttitudeSkill> findByAssessmentYear(Integer assessmentYear);
    void deleteByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);

}
