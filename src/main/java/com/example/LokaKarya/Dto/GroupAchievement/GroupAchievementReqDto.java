package com.example.LokaKarya.Dto.GroupAchievement;

import com.example.LokaKarya.Entity.GroupAchievement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAchievementReqDto {
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;

    public static GroupAchievement toEntity(GroupAchievementReqDto groupAchievementDto) {
        GroupAchievement groupAchievement = new GroupAchievement();
        groupAchievement.setGroupName(groupAchievementDto.getGroupName());
        groupAchievement.setPercentage(groupAchievementDto.getPercentage());
        groupAchievement.setEnabled(groupAchievementDto.getEnabled());
        return groupAchievement;
    }
}

