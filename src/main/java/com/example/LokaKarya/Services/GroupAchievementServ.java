package com.example.LokaKarya.Services;

import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementDto;
import com.example.LokaKarya.Dto.GroupAchievement.GroupAchievementReqDto;

import java.util.List;
import java.util.UUID;

public interface GroupAchievementServ {
    List<GroupAchievementDto> getAllGroupAchievement();
    GroupAchievementDto getGroupAchievementById(UUID id);
    GroupAchievementDto createGroupAchievement(GroupAchievementReqDto groupAchievementDto);
    GroupAchievementDto updateGroupAchievement(UUID id, GroupAchievementReqDto groupAchievementDto);
    Boolean deleteGroupAchievement(UUID id);
}

