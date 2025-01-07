package com.example.lokakarya.Repository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.lokakarya.Entity.GroupAttitudeSkill;

@Repository
public interface GroupAttitudeSkillRepo extends JpaRepository<GroupAttitudeSkill, UUID> {
    List<GroupAttitudeSkill> findByEnabled(Integer enabled);
    Page<GroupAttitudeSkill> findByGroupNameContainingIgnoreCase(String groupName, Pageable pageable);
    Page<GroupAttitudeSkill> findByPercentage(Double percentage, Pageable pageable);
    long countByGroupNameContainingIgnoreCase(String groupName);
}