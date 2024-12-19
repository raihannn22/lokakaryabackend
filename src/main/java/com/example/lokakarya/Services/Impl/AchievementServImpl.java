package com.example.lokakarya.Services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lokakarya.Dto.Achievement.AchievementDto;
import com.example.lokakarya.Dto.Achievement.AchievementReqDto;
import com.example.lokakarya.Entity.Achievement;
import com.example.lokakarya.Entity.GroupAchievement;
import com.example.lokakarya.Repository.AchievementRepo;
import com.example.lokakarya.Repository.GroupAchievementRepo;
import com.example.lokakarya.Services.AchievementServ;
import com.example.lokakarya.util.GetUserUtil;

@Service
public class AchievementServImpl implements AchievementServ {
    private final Logger Log = LoggerFactory.getLogger(AchievementServImpl.class);

    @Autowired
    AchievementRepo achievementRepo;

    @Autowired
    GroupAchievementRepo groupAchievementRepo;

    @Autowired
    GetUserUtil getUserUtil;

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
    public List<AchievementReqDto> getAchievementsByGroupId(UUID groupId) {
        List<Achievement> achievements = achievementRepo.findByGroupAchievementId(groupId);
        List<AchievementReqDto> achievementReqDtos = new ArrayList<>();
        for (Achievement achievement : achievements) {
            achievementReqDtos.add(AchievementReqDto.fromEntity(achievement));
        }
        return achievementReqDtos;
    }




    @Override
    public AchievementReqDto createAchievement(AchievementDto achievementDto) {
        Optional<GroupAchievement> groupAchievement = groupAchievementRepo.findById(achievementDto.getGroupId());
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        if (groupAchievement.isPresent()) {
            Achievement achievement = achievementDto.toEntity(achievementDto, groupAchievement.get(), null, null, currentUser, new java.util.Date());
            achievementRepo.save(achievement);
            return AchievementReqDto.fromEntity(achievementRepo.save(achievement));
        }else {
            throw new RuntimeException("Achievement not found");
        }
    }

    @Override
    public AchievementReqDto updateAchievement(UUID id, AchievementDto achievementDto) {
        Log.info("Start updateAchievement in AchievementServImpl");
        UUID currentUser = getUserUtil.getCurrentUser().getId();
        Achievement achievement = achievementRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));
        achievement.setAchievement(achievementDto.getAchievement());     
        achievement.setGroupAchievement(groupAchievementRepo.findById(achievementDto.getGroupId()).orElseThrow(() -> new RuntimeException("Group Achievement not found")));
        achievement.setEnabled(achievementDto.getEnabled());
        achievement.setUpdatedBy(currentUser);
        achievement.setUpdatedAt(new java.util.Date());
        achievementRepo.save(achievement);
        Log.info("End updateachievement in achievementServImpl");
        return AchievementReqDto.fromEntity(achievementRepo.save(achievement));
    }

    @Override
public Boolean deleteAchievement(UUID id) {
    Log.info("Start deleteAchievement in AchievementServImpl");

    if (achievementRepo.existsById(id)) {
        achievementRepo.deleteById(id);  // hanya menghapus achievement berdasarkan id
        Log.info("End deleteAchievement in AchievementServImpl");
        return true;
    }
    throw new RuntimeException("Achievement not found");
}
    
}