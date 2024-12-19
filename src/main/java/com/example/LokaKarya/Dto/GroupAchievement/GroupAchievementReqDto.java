package com.example.lokakarya.Dto.GroupAchievement;

import com.example.lokakarya.Entity.GroupAchievement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAchievementReqDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;

    public static GroupAchievementReqDto fromEntity(GroupAchievement groupAchievement) {
        GroupAchievementReqDto groupAchievementReqDto = new GroupAchievementReqDto();
        groupAchievementReqDto.setId(groupAchievement.getId());
        groupAchievementReqDto.setGroupName(groupAchievement.getGroupName());
        groupAchievementReqDto.setPercentage(groupAchievement.getPercentage());
        groupAchievementReqDto.setEnabled(groupAchievement.getEnabled());
        return groupAchievementReqDto;
    }
}

