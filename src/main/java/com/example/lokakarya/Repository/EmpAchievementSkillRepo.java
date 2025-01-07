package com.example.lokakarya.Repository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.EmpAchievementSkill;

@Repository
public interface EmpAchievementSkillRepo extends JpaRepository<EmpAchievementSkill, UUID> {
    List<EmpAchievementSkill> findByUserId(UUID userId);
    List<EmpAchievementSkill> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
    List<EmpAchievementSkill> findByAssessmentYear(Integer assessmentYear);
    void deleteByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
 List<EmpAchievementSkill> findByUserId(UUID userId);
 List<EmpAchievementSkill> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
// List<EmpAchievementSkill> findByUserIdAndAssessmentYearAndEnabled(UUID userId, Integer assessmentYear);
 List<EmpAchievementSkill> findByAssessmentYear(Integer assessmentYear);
 void deleteByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
}

