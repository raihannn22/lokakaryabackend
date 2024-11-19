package com.example.LokaKarya.Services.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.GroupAchievement;
import com.example.LokaKarya.Repository.AchievementRepo;
import com.example.LokaKarya.Repository.GroupAchievementRepo;
import com.example.LokaKarya.Services.AchievementServ;

@Service
public class AchievementServImpl implements AchievementServ {
    private final Logger Log = LoggerFactory.getLogger(AssessmentSummaryServImpl.class);

    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    GroupAchievementRepo groupAchievementRepo;

    @Override
    public List<AchievementReqDto> getAllAchievement() {
        List<Achievement> response = achievementRepo.findAll();
        List<AchievementReqDto> achievementReqDto = new ArrayList<>();
        for (Achievement achievement : response) {
            achievementReqDto.add(AchievementReqDto.fromEntity(achievement));
        }
        return achievementReqDto;
    }

    @Override
    public AchievementReqDto getAchievementById(UUID id) {
        Log.info("Start getAchievementById in AchievementServImpl");
        Achievement achievement = achievementRepo.findById(id).orElseThrow(() -> new RuntimeException("Achievement not found"));
        Log.info("End getAchievementById in AchievementServImpl");
        return AchievementReqDto.fromEntity(achievement);
    }

    @Override
    public AchievementReqDto createAchievement(AchievementDto achievementDto) {
        Optional<GroupAchievement> user = groupAchievementRepo.findById(achievementDto.getGroupId());
        if (user.isPresent()) {
            Achievement achievement = achievementDto.toEntity(achievementDto, user.get(), UUID.randomUUID(), Date.valueOf(LocalDate.now()), user.get().getId(), Date.valueOf(LocalDate.now()));
            achievementRepo.save(achievement);
            return AchievementReqDto.fromEntity(achievementRepo.save(achievement));
        }else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public AchievementReqDto updateAchievement(UUID id, AchievementDto achievementDto) {
        Log.info("Start updateAchievement in AchievementServImpl");

        Achievement achievement = achievementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
        achievement.setAchievement(achievementDto.getAchievement());     
        achievement.setGroupAchievement(groupAchievementRepo.findById(achievementDto.getGroupId()).orElseThrow(() -> new RuntimeException("Group Achievement not found")));
        achievement.setEnabled(achievementDto.getEnabled());
        achievement.setUpdatedBy(UUID.randomUUID());
        achievement.setUpdatedAt(Date.valueOf(LocalDate.now()));
        achievementRepo.save(achievement);
        Log.info("End updateachievement in achievementServImpl");
        return AchievementReqDto.fromEntity(achievementRepo.save(achievement));
    }

    @Override
    public Boolean deleteAchievement(UUID id) {
        Log.info("Start deleteAchievement in AchievementServImpl");
        if (achievementRepo.existsById(id)) {
            achievementRepo.deleteById(id);
            Log.info("End deleteAchievement in AchievementServImpl");
            return true;
        }
        throw new RuntimeException("Achievement not found");
    }
}