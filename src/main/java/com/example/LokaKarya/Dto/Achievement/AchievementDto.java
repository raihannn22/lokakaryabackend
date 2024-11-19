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

    // public static AssessmentSummary toEntity(AssessmentSummaryDto assessmentSummaryDto, User user, UUID updatedBy
    // , Date updatedAt, UUID createdBy, Date createdAt) {
    //     AssessmentSummary assessmentSummary = new AssessmentSummary();
    //     assessmentSummary.setId(assessmentSummaryDto.getId());
    //     assessmentSummary.setUser(user);
    //     assessmentSummary.setYear(assessmentSummaryDto.getYear());
    //     assessmentSummary.setScore(assessmentSummaryDto.getScore());
    //     assessmentSummary.setStatus(assessmentSummaryDto.getStatus());
    //     assessmentSummary.setUpdatedBy(updatedBy);
    //     assessmentSummary.setUpdatedAt(updatedAt);
    //     assessmentSummary.setCreatedBy(createdBy);
    //     assessmentSummary.setCreatedAt(createdAt);
    //     return assessmentSummary;
    // }

    //     public static AchievementDto fromEntity(Achievement achievement) {
    //     AchievementDto achievementDto = new AchievementDto();
    //     achievementDto.setId(achievement.getId());
    //     achievementDto.setAchievement(achievement.getAchievement());
    //     achievementDto.setGroupAchievement(GroupAchievement);
    //     achievementDto.setEnabled(achievement.getEnabled());
    //     return achievementDto;
    // }
}

