package com.example.LokaKarya.Dto.Achievement;

import com.example.LokaKarya.Dto.AssessmentSummary.AssessmentSummaryReqDto;
import com.example.LokaKarya.Entity.Achievement;
import com.example.LokaKarya.Entity.AssessmentSummary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchievementReqDto {
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("achievement")
    private String achievement;
    @JsonProperty("group_id")
    private UUID groupId;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("enabled")
    private Integer enabled;

    public static AchievementReqDto fromEntity(Achievement achievement) {
        AchievementReqDto achievementReqDto = new AchievementReqDto();
        achievementReqDto.setId(achievement.getId());
        achievementReqDto.setGroupName(achievement.getGroupAchievement().getGroupName());
        achievementReqDto.setAchievement(achievement.getAchievement());
        achievementReqDto.setGroupId(achievement.getGroupAchievement().getId());
        achievementReqDto.setEnabled(achievement.getEnabled());
        return achievementReqDto;
    }


}

