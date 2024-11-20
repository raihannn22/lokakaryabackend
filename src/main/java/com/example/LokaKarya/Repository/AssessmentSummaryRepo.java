package com.example.LokaKarya.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LokaKarya.Entity.AssessmentSummary;

@Repository
public interface AssessmentSummaryRepo extends JpaRepository<AssessmentSummary, UUID> {
}
