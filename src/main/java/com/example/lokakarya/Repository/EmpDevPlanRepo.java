package com.example.lokakarya.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.EmpDevPlan;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmpDevPlanRepo extends JpaRepository <EmpDevPlan, UUID> {
    void deleteByUserId (UUID userId);
    void deleteByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
    List<EmpDevPlan> findEmpDevPlanByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
}