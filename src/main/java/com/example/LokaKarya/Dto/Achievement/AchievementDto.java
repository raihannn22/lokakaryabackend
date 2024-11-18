package com.example.LokaKarya.Dto.Achievement;

import com.example.LokaKarya.Entity.Achievement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

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


    public static AchievementDto fromEntity(Achievement achievement) {
        AchievementDto achievementDto = new AchievementDto();
        achievementDto.setId(achievement.getId());
        achievementDto.setAchievement(achievement.getAchievement());
        achievementDto.setGroupId(achievement.getGroupId());
        achievementDto.setEnabled(achievement.getEnabled());
        return achievementDto;
    }

    public static Achievement toEntity(AchievementDto achievementDto) {
        Achievement achievement = new Achievement();
        achievement.setId(achievementDto.getId());
        achievement.setAchievement(achievementDto.getAchievement());
        achievement.setGroupId(achievementDto.getGroupId());
        achievement.setEnabled(achievementDto.getEnabled());
        return achievement;
    }
}

