package com.example.lokakarya.Dto.GroupAttitudeSkill;

import com.example.lokakarya.Entity.GroupAttitudeSkill;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;


    
    public static GroupAttitudeSkill toEntity(GroupAttitudeSkillDto groupAttitudeSkillDto, UUID updateBy, Date updateAt, UUID createdBy, Date createdAt) {
        GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
        groupAttitudeSkill.setId(groupAttitudeSkillDto.getId());
        groupAttitudeSkill.setGroupName(groupAttitudeSkillDto.getGroupName());
        groupAttitudeSkill.setPercentage(groupAttitudeSkillDto.getPercentage());
        groupAttitudeSkill.setEnabled(groupAttitudeSkillDto.getEnabled());
        groupAttitudeSkill.setCreatedAt(createdAt);
        groupAttitudeSkill.setCreatedBy(createdBy);
        groupAttitudeSkill.setUpdatedAt(updateAt);
        groupAttitudeSkill.setUpdatedBy(updateBy);
        return groupAttitudeSkill;
    }
}

