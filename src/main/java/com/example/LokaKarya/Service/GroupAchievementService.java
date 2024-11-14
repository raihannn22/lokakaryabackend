package com.example.LokaKarya.Service;

import com.example.LokaKarya.Dto.GroupAchievementDto;
import java.util.List;
import java.util.UUID;

public interface GroupAchievementService {

    List<GroupAchievementDto> getAllGroupAchievements();

    GroupAchievementDto getGroupAchievementById(UUID id);

    GroupAchievementDto createGroupAchievement(GroupAchievementDto groupAchievementDto);

    GroupAchievementDto updateGroupAchievement(UUID id, GroupAchievementDto groupAchievementDto);

    void deleteGroupAchievement(UUID id);
}
