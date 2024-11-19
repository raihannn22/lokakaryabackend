package com.example.LokaKarya.Repository;

import com.example.LokaKarya.Entity.AssessmentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssessmentSummaryRepo extends JpaRepository<AssessmentSummary, UUID> {
}
