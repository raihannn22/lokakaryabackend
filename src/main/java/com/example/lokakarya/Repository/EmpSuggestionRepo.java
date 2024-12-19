package com.example.lokakarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.EmpSuggestion;

@Repository
public interface EmpSuggestionRepo extends JpaRepository<EmpSuggestion, UUID> {
    List<EmpSuggestion> findByUserId(UUID userId);
    List<EmpSuggestion> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);

    void deleteByAssessmentYearAndUserId(Integer assessmentYear, UUID id);

}