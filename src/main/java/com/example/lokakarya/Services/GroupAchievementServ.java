package com.example.lokakarya.Services;

import java.util.List;
import java.util.UUID;

import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementReqDto;

public interface GroupAchievementServ {
    List<GroupAchievementReqDto> getAllGroupAchievement();
    GroupAchievementReqDto getGroupAchievementById(UUID id);
    GroupAchievementReqDto createGroupAchievement(GroupAchievementDto groupAchievementDto);
    GroupAchievementReqDto updateGroupAchievement(UUID id, GroupAchievementDto groupAchievementDto);
    Boolean deleteGroupAchievement(UUID id);
    List<GroupAchievementReqDto> getAllGroupAchievementEnabled();
    List<GroupAchievementReqDto> getPaginatedGroupAchievement(int page, int size, String sort, String direction, String searchKeyword); // Hapus percentage
    long count();
    long countBySearchKeyword(String searchKeyword); // Metode untuk menghitung berdasarkan pencarian
    
}

