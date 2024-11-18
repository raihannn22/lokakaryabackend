package com.example.LokaKarya.Services.Impl;

import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Repository.AchievementRepo;
import com.example.LokaKarya.Services.AchievementServ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AchievementServImpl implements AchievementServ {

    private final Logger Log = LoggerFactory.getLogger(GroupAchievementServImpl.class);

    @Autowired
    private AchievementRepo achievementRepo;

    @Override
    public List<AchievementDto> getAllAchievement() {
       Log.info("Start getAllpAchievement in AchievementServImpl");
        List<Achievement> response = achievementRepo.findAll();
        List<AchievementDto> achievementList = new ArrayList<>();

        for (Achievement achievement : response) {
            AchievementDto achievementDto = AchievementDto.fromEntity(achievement);
            achievementList.add(achievementDto);
        }
       Log.info("End getAllAchievement in AchievementServImpl");
        return achievementList;
    }

    @Override
    public AchievementDto getAchievementById(UUID id) {
       Log.info("Start getAchievementById in AchievementServImpl");
        Optional<Achievement> achivement = achievementRepo.findById(id);
       Log.info("End getAchievementById in AchievementServImpl");
        return achivement.map(AchievementDto::fromEntity).orElse(null);
    }

    @Override
    public AchievementDto createAchievement(AchievementReqDto achievementDto) {
       Log.info("Start createAchievement in AchievementServImpl");

        Achievement achievement = AchievementReqDto.toEntity(achievementDto);

        achievementRepo.save(achievement);
       Log.info("End createAchievement in AchievementServImpl");
        return AchievementDto.fromEntity(achievement);
    }

    @Override
    public AchievementDto updateAchievement (UUID id, AchievementReqDto achievementDto) {
       Log.info("Start updateAchievement in AchievementServImpl");
        Achievement findAchievement  = achievementRepo.findById(id).orElseThrow(() -> new RuntimeException("Achievement not found"));

        updateAchievementFields(findAchievement , achievementDto);

        achievementRepo.save(findAchievement);
       Log.info("End updateAchievement in AchievementServImpl");
        return AchievementDto.fromEntity(findAchievement);
    }

    @Override
    public Boolean deleteAchievement(UUID id) {
       Log.info("Start deleteAchievement in AchievementServImpl");
        Achievement findAchievement = achievementRepo.findById(id).orElseThrow(() -> new RuntimeException("Achievement not found"));
        achievementRepo.delete(findAchievement);
       Log.info("End deleteAchievement in AchievementServImpl");
        return true;
    }

    private void updateAchievementFields(Achievement existingAchievement, AchievementReqDto achievementDto) {
        if (achievementDto.getAchievement() != null) {
            existingAchievement.setAchievement(achievementDto.getAchievement());
        }
        if (achievementDto.getGroupId() != null) {
            existingAchievement.setGroupId(achievementDto.getGroupId());
        }
        if (achievementDto.getEnabled() != null) {
            existingAchievement.setEnabled(achievementDto.getEnabled());
        }
        

    }
}
