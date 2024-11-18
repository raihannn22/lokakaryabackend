package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;

import java.util.List;
import java.util.UUID;

public interface AchievementServ {
    List<AchievementDto> getAllAchievement();
    AchievementDto getAchievementById(UUID id);
    AchievementDto createAchievement(AchievementReqDto achievementDto);
    AchievementDto updateAchievement(UUID id, AchievementReqDto achievementDto);
    Boolean deleteAchievement(UUID id);
}

