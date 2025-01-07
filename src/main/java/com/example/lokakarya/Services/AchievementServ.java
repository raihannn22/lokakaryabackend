package com.example.lokakarya.Services;
import java.util.List;
import java.util.UUID;
import com.example.lokakarya.Dto.Achievement.AchievementDto;
import com.example.lokakarya.Dto.Achievement.AchievementReqDto;

public interface AchievementServ {
    List<AchievementReqDto> getAllAchievement();
    AchievementReqDto getAchievementById(UUID id);
    List<AchievementReqDto> getAchievementsByGroupId(UUID groupId);
    AchievementReqDto createAchievement(AchievementDto achievementDto);
    AchievementReqDto updateAchievement(UUID id, AchievementDto achievementDto);
    Boolean deleteAchievement(UUID id);
    List<AchievementReqDto> getAllAchievementEnabled();
    List<AchievementReqDto> getPaginatedAchievement(int page, int size, String sort, String direction, String searchKeyword); 
    long count();
    long countBySearchKeyword(String searchKeyword); 
}