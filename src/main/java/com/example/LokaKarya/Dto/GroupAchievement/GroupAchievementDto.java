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
public class GroupAchievementDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;


    public static GroupAchievementDto fromEntity(GroupAchievement groupAchievement) {
        GroupAchievementDto groupAchievementDto = new GroupAchievementDto();
        groupAchievementDto.setId(groupAchievement.getId());
        groupAchievementDto.setGroupName(groupAchievement.getGroupName());
        groupAchievementDto.setPercentage(groupAchievement.getPercentage());
        groupAchievementDto.setEnabled(groupAchievement.getEnabled());
        return groupAchievementDto;
    }

    public static GroupAchievement toEntity(GroupAchievementDto groupAchievementDto) {
        GroupAchievement groupAchievement = new GroupAchievement();
        groupAchievement.setId(groupAchievementDto.getId());
        groupAchievement.setGroupName(groupAchievementDto.getGroupName());
        groupAchievement.setPercentage(groupAchievementDto.getPercentage());
        groupAchievement.setEnabled(groupAchievementDto.getEnabled());
        return groupAchievement;
    }
}

