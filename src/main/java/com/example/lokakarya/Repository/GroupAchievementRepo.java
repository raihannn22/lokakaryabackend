package com.example.lokakarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.GroupAchievement;

@Repository
public interface GroupAchievementRepo extends JpaRepository<GroupAchievement, UUID> {
    List<GroupAchievement> findByEnabled(Integer groupEnabled);

    // Metode untuk mencari berdasarkan column
    Page<GroupAchievement> findByGroupNameContainingIgnoreCase(String groupName, Pageable pageable);
    Page<GroupAchievement> findByPercentage(Double percentage, Pageable pageable);
    
    // Metode untuk menghitung berdasarkan groupName
    long countByGroupNameContainingIgnoreCase(String groupName);
    // long countDistinctById(UUID id);

}
