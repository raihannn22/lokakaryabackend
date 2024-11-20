package com.example.LokaKarya.Dto.Achievement;

import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.AssessmentSummary;
import com.example.LokaKarya.Entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.sql.Date;
import java.util.UUID;

import com.example.LokaKarya.Entity.GroupAchievement;

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

