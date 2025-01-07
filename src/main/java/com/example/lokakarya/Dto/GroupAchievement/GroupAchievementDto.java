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
public class GroupAchievementDto {
    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("group_name")
    private String groupName;
    
    @JsonProperty("percentage")
    private Integer percentage;
    
    @JsonProperty("enabled")
    private Integer enabled;
    
    public static GroupAchievement toEntity(GroupAchievementDto groupAchievementDto, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        GroupAchievement groupAchievement = new GroupAchievement();
        groupAchievement.setId(groupAchievementDto.getId());
        groupAchievement.setGroupName(groupAchievementDto.getGroupName());
        groupAchievement.setPercentage(groupAchievementDto.getPercentage());
        groupAchievement.setEnabled(groupAchievementDto.getEnabled());
        groupAchievement.setCreatedAt(createdAt);
        groupAchievement.setCreatedBy(createdBy);
        groupAchievement.setUpdatedAt(updateAt);
        groupAchievement.setUpdatedBy(updateBy);
        return groupAchievement;
    }
}