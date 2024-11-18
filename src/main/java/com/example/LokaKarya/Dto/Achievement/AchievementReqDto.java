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
public class AchievementReqDto {
    @JsonProperty("achievement")
    private String achievement;
    @JsonProperty("group_id")
    private UUID groupId;
    @JsonProperty("enabled")
    private Integer enabled;

    public static Achievement toEntity(AchievementReqDto achievementDto) {
        Achievement achievement = new Achievement();
        achievement.setAchievement(achievementDto.getAchievement());
        achievement.setGroupId(achievementDto.getGroupId());
        achievement.setEnabled(achievementDto.getEnabled());
        return achievement;
    }
}

