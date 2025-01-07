package com.example.lokakarya.Dto.Achievement;
import com.example.lokakarya.Entity.Achievement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
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
    @JsonProperty("group_percentage")
    private Integer groupPercentage;
    @JsonProperty("group_enabled")
    private Integer groupEnabled;
    @JsonProperty("enabled")
    private Integer enabled;

    public static AchievementReqDto fromEntity(Achievement achievement) {
        AchievementReqDto achievementReqDto = new AchievementReqDto();
        achievementReqDto.setId(achievement.getId());
        achievementReqDto.setGroupName(achievement.getGroupAchievement().getGroupName());
        achievementReqDto.setGroupPercentage(achievement.getGroupAchievement().getPercentage());
        achievementReqDto.setAchievement(achievement.getAchievement());
        achievementReqDto.setGroupId(achievement.getGroupAchievement().getId());
        achievementReqDto.setGroupEnabled(achievement.getGroupAchievement().getEnabled());
        achievementReqDto.setEnabled(achievement.getEnabled());
        return achievementReqDto;
    }
}