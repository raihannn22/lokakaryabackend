package com.example.lokakarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.Achievement;
import com.example.lokakarya.Entity.AttitudeSkill;

@Repository
public interface AttitudeSkillRepo extends JpaRepository<AttitudeSkill, UUID> {
    List<AttitudeSkill> findByEnabled(Integer enabled);

    Page<AttitudeSkill> findByAttitudeSkillContainingIgnoreCase(String attitudeSkill, Pageable pageable);

    long countByAttitudeSkillContainingIgnoreCase(String attitudeSkill);
}
