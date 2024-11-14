package com.example.LokaKarya.Repository;

import java.util.UUID;

import com.example.LokaKarya.Entity.tbl_group_achievement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupAchievementRepo extends JpaRepository<tbl_group_achievement, UUID> {
}
