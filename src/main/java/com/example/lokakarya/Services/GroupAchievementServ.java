package com.example.lokakarya.Services;

import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.lokakarya.Dto.GroupAchievement.GroupAchievementReqDto;

import java.util.List;
import java.util.UUID;

public interface GroupAchievementServ {
    List<GroupAchievementReqDto> getAllGroupAchievement();
    GroupAchievementReqDto getGroupAchievementById(UUID id);
    GroupAchievementReqDto createGroupAchievement(GroupAchievementDto groupAchievementDto);
    GroupAchievementReqDto updateGroupAchievement(UUID id, GroupAchievementDto groupAchievementDto);
    Boolean deleteGroupAchievement(UUID id);
}

