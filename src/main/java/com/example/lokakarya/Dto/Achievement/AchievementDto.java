package com.example.lokakarya.Dto.Achievement;

import com.example.lokakarya.Entity.Achievement;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.Date;
import java.util.UUID;

import com.example.lokakarya.Entity.GroupAchievement;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchievementDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("achievement")
    private String achievement;
    @JsonProperty("group_id")
    private UUID groupId;
    @JsonProperty("enabled")
    private Integer enabled;




    public static Achievement toEntity(AchievementDto achievementDto, GroupAchievement groupAchievement, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        Achievement achievement = new Achievement();
        achievement.setId(achievementDto.getId());
        achievement.setAchievement(achievementDto.getAchievement());
        achievement.setGroupAchievement(groupAchievement);
        achievement.setEnabled(achievementDto.getEnabled());
        achievement.setCreatedAt(createdAt);
        achievement.setCreatedBy(createdBy);
        achievement.setUpdatedAt(updateAt);
        achievement.setUpdatedBy(updateBy);
        return achievement;
    }


    //     public static AchievementDto fromEntity(Achievement achievement) {
    //     AchievementDto achievementDto = new AchievementDto();
    //     achievementDto.setId(achievement.getId());
    //     achievementDto.setAchievement(achievement.getAchievement());
    //     achievementDto.setGroupAchievement(GroupAchievement);
    //     achievementDto.setEnabled(achievement.getEnabled());
    //     return achievementDto;
    // }
}

