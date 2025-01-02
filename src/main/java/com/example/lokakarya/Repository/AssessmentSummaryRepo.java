package com.example.lokakarya.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.AssessmentSummary;

@Repository
public interface AssessmentSummaryRepo extends JpaRepository<AssessmentSummary, UUID> {
    Optional<AssessmentSummary> findByUserIdAndYear(UUID userId, int year);
    void deleteByUserIdAndYear(UUID userId, int year);
    List<AssessmentSummary> findByYear(int year);

}
