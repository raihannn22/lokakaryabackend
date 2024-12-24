package com.example.lokakarya.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lokakarya.Entity.GroupAchievement;

@Repository
public interface GroupAchievementRepo extends JpaRepository<GroupAchievement, UUID> {
    List<GroupAchievement> findByEnabled(Integer groupEnabled);
}
