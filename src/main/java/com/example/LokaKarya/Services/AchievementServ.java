package com.example.LokaKarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.LokaKarya.Dto.Achievement.AchievementDto;
import com.example.LokaKarya.Dto.Achievement.AchievementReqDto;

public interface AchievementServ {
    List<AchievementReqDto> getAllAchievement();
    AchievementReqDto getAchievementById(UUID id);
    AchievementReqDto createAchievement(AchievementDto achievementDto);
    AchievementReqDto updateAchievement(UUID id, AchievementDto achievementDto);
    Boolean deleteAchievement(UUID id);
}